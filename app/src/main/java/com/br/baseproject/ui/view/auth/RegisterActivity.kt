package com.br.baseproject.ui.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.br.baseproject.R
import com.br.baseproject.databinding.ActivityRegisterBinding
import com.br.baseproject.helper.FirebaseHelper
import com.br.baseproject.helper.showBottomSheet
import com.br.baseproject.ui.view.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private var binding: ActivityRegisterBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initClicks()
        iniciaToolbar()
        auth = Firebase.auth
    }

    private fun initClicks() {
        binding?.btnRegister?.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding?.edtEmail?.text.toString().trim()
        val password = binding?.edtPassword?.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                //hideKeyboard()
                binding?.progressBar?.isVisible = true
                registerUser(email, password)

            } else {
                showBottomSheet(message = R.string.text_password_empty_register)
            }
        } else {
            showBottomSheet(message = R.string.text_email_empty_register)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, DashboardActivity::class.java))
                } else {
                    showBottomSheet(message =
                    FirebaseHelper.validError(task.exception?.message ?: ""))
                    binding?.progressBar?.isVisible = false
                }
            }
        }

    private fun iniciaToolbar() {
        val toolbar = binding?.toolbar
        toolbar?.title = ""
        setSupportActionBar(toolbar)
    }
}