package com.br.baseproject.ui.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.br.baseproject.R
import com.br.baseproject.databinding.ActivityLoginBinding
import com.br.baseproject.helper.FirebaseHelper
import com.br.baseproject.helper.showBottomSheet
import com.br.baseproject.ui.view.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        initClicks()
        changeStatusBar()
    }

    private fun initClicks() {
        binding.btnCriarConta.setOnClickListener { validateData() }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.btnRecover.setOnClickListener {
            startActivity(Intent(this, RecoverAccountActivity::class.java))
        }
    }

    private fun validateData() {
        val email=binding.editEmail.text.toString().trim()
        val password=binding.editSenha.text.toString().trim()
        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                binding.progressBar.isVisible = true
                loginUser(email, password)
            } else {
                showBottomSheet(message = R.string.text_password_empty_login)
            }
        } else {
            showBottomSheet(message = R.string.text_email_empty_login)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { login ->
                if (login.isSuccessful) {
                    startActivity(Intent(this, DashboardActivity::class.java))
                } else {
                    showBottomSheet(message = FirebaseHelper.validError(login.exception?.message ?: ""))
                    binding.progressBar.isVisible = false
                }
            }
        }

    private fun changeStatusBar() {
        window.statusBarColor = getColor(R.color.black)
    }
}