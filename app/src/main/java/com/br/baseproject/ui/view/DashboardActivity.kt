package com.br.baseproject.ui.view

import android.content.Intent
import com.br.baseproject.databinding.ActivityDashboardBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.baseproject.R
import com.br.baseproject.helper.showBottomSheet
import com.br.baseproject.ui.view.auth.LoginActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateForm()
        navigateHome()
        navigatePerfil()
        navigateSettings()
        iniciaDados()
        navigateInfo()
        changeStatusBar()
        deslogar()
    }

    private fun iniciaDados() {
        val intent = intent.extras
        if(intent != null) {
            val username = intent.getString("username")
            setUserName(username!!)
        }
    }

    private fun setUserName(username: String) {
        binding.tvUsername.text= username
    }

    private fun navigateHome() {
        binding.home.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun navigateForm() {
        binding.cadastros.setOnClickListener {
            startActivity(Intent(this, NewWordActivity::class.java))
        }
    }

    private fun deslogar() {
        binding.ibLogout.setOnClickListener {
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
    private fun navigateInfo() {
        binding.info.setOnClickListener {
            showBottomSheet(message = R.string.error_not_impl_yet)
        }
    }

    private fun changeStatusBar() {
        window.statusBarColor = getColor(R.color.black)
    }
}