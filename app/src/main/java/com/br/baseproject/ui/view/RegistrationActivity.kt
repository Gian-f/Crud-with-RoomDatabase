package com.br.baseproject.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.result.contract.ActivityResultContracts
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.baseproject.application.RegistrationApplication
import com.br.baseproject.database.model.Registry
import com.br.baseproject.databinding.ActivityMainBinding
import com.br.baseproject.ui.adapter.WordListAdapter
import com.br.baseproject.ui.viewmodel.RegistryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: WordListAdapter

    private val registryViewModel: RegistryViewModel by viewModels {
        RegistryViewModel.WordViewModelFactory((application as RegistrationApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        iniciaToolbar()
        refreshApp()
        this.recyclerview=this.binding.recyclerview
        this.adapter=WordListAdapter()
        this.recyclerview.adapter=this.adapter
        this.recyclerview.layoutManager=LinearLayoutManager(this)

        val resultLauncher=
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let {
                        it.getStringExtra("NOME")?.let { nome ->
                            Registry(
                                nome,
                                it.getStringExtra("TELEFONE"),
                                it.getStringExtra("DATA")
                            )
                        }?.let { it2 ->
                            registryViewModel.insert(it2)
                        }
                    }
                }
            }
        this.binding.fab.setOnClickListener {
            resultLauncher.launch(Intent(this, NewRegistrationActivity::class.java))
        }
    }

    private fun iniciaToolbar() {
        val toolbar=binding.toolbar
        toolbar.title=""
        setSupportActionBar(toolbar)
    }

    private fun loadData() {
        registryViewModel.findAll()
    }

    private fun refreshApp() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler(mainLooper).postDelayed({loadData()},4000)
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        registryViewModel.allWords.observe(this) { words ->
            words?.let { adapter.submitList(it) }
        }
    }
}