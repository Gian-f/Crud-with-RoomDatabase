package com.br.baseproject.ui.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.br.baseproject.R
import com.br.baseproject.databinding.ActivityRecoverAccountBinding
import com.br.baseproject.helper.FirebaseHelper
import com.br.baseproject.helper.showBottomSheet
import com.google.firebase.auth.FirebaseAuth

class RecoverAccountActivity : AppCompatActivity() {
    private var binding: ActivityRecoverAccountBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRecoverAccountBinding.inflate(layoutInflater)
        setContentView(this.binding!!.root)
        initClicks()
    }

    private fun initClicks() {
        binding?.btnRecover?.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding?.edtEmail?.text.toString().trim()

        if (email.isNotEmpty()) {
            //hideKeyboard()

            binding?.progressBar?.isVisible = true

            recoverAccountUser(email)
        } else {
            showBottomSheet(message = R.string.text_email_empty_recover_account)
        }
    }

    private fun recoverAccountUser(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showBottomSheet(message = R.string.text_email_send_sucess_recover_account)
                } else {
                    showBottomSheet(message = FirebaseHelper.validError(task.exception?.message ?: ""))
                }
                binding?.progressBar?.isVisible = false
            }
        }
}