package com.example.roomdatabasemvvm.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasemvvm.application.WordApplication
import com.example.roomdatabasemvvm.database.model.Word
import com.example.roomdatabasemvvm.databinding.ActivityMainBinding
import com.example.roomdatabasemvvm.ui.adapter.WordListAdapter
import com.example.roomdatabasemvvm.ui.viewmodel.WordViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: WordListAdapter

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModel.WordViewModelFactory((application as WordApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        iniciaToolbar()

        this.recyclerview = this.binding.recyclerview
        this.adapter = WordListAdapter()
        this.recyclerview.adapter = this.adapter
        this.recyclerview.layoutManager = LinearLayoutManager(this)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let{
                    it.getStringExtra("REPLY")?.let { it1 -> Word(it1) }?.let { it2 ->
                        wordViewModel.insert(it2)
                    }
                }
            }
        }
        this.binding.fab.setOnClickListener {
            resultLauncher.launch(Intent(this, NewWordActivity::class.java))
        }
    }

    private fun iniciaToolbar() {
        val toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.submitList(it)}
        })
    }
}