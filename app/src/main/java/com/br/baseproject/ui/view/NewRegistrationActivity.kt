package com.br.baseproject.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.br.baseproject.R
import com.br.baseproject.application.RegistrationApplication
import com.br.baseproject.database.model.Registry
import com.br.baseproject.databinding.ActivityNewWordBinding
import com.br.baseproject.helper.showBottomSheet
import com.br.baseproject.ui.viewmodel.RegistryViewModel
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class NewRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewWordBinding
    private val registryViewModel: RegistryViewModel by viewModels {
        RegistryViewModel.WordViewModelFactory((application as RegistrationApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        iniciaToolbar()
    }

    override fun onStart() {
        super.onStart()
        this.binding.btnSave.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(this.binding.edtNome.text) || this.binding.edtTelefone.text.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, intent)
                showBottomSheet(message = R.string.error_empty_fields)
            } else {
                val nome = binding.edtNome.text.toString().trim()
                val telefone = binding.edtTelefone.text.toString().trim()
                val data = SimpleDateFormat("dd/MM/yyyy").format(Date())
                Handler(mainLooper).postDelayed({ },4000)
                this.binding.progressBar.visibility = View.VISIBLE
                intent.putExtra("NOME",nome)
                intent.putExtra("TELEFONE",telefone)
                intent.putExtra("DATA", data)
                setResult(Activity.RESULT_OK,intent)
                Toast.makeText(this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }


    private fun iniciaToolbar() {
        val toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }
}