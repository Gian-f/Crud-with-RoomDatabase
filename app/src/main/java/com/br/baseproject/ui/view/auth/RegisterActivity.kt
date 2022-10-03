package com.br.baseproject.ui.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.baseproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}