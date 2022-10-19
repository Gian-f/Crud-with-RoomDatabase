package com.br.baseproject.ui.view

import android.content.Intent
import com.br.baseproject.databinding.ActivityDashboardBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.baseproject.R
import com.br.baseproject.helper.showBottomSheet
import com.br.baseproject.ui.view.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        navigateForm()
        navigateHome()
        navigatePerfil()
        navigateSettings()
        iniciaDados()
        navigateChat()
        changeStatusBar()
        deslogar()
    }

    private fun iniciaDados() {
        val username = auth.currentUser?.email
        if(username != null) {
            setUserName(username)
        }
    }

    private fun setUserName(username: String) {
        binding.tvUsername.text= username
    }

    private fun navigateHome() {
        binding.home.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }
    }

    private fun navigateForm() {
        binding.cadastros.setOnClickListener {
            startActivity(Intent(this, NewRegistrationActivity::class.java))
        }
    }

    private fun deslogar() {
        binding.ibLogout.setOnClickListener {
            auth.signOut()
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun navigateSettings() {
        binding.settings.setOnClickListener {
            showBottomSheet(message = R.string.error_not_impl_yet)
        }
    }

    private fun navigatePerfil() {
        binding.perfil.setOnClickListener {
            showBottomSheet(message = R.string.error_not_impl_yet)
        }
    }
    private fun navigateChat() {
        binding.chats.setOnClickListener {
            showBottomSheet(message = R.string.error_not_impl_yet)
            //startActivity(Intent(this, ChatActivity::class.java))
        }
    }

    private fun changeStatusBar() {
        window.statusBarColor = getColor(R.color.black)
    }
}