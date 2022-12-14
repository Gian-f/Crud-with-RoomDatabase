package com.br.baseproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.br.baseproject.R
import com.br.baseproject.databinding.ActivitySplashBinding
import com.br.baseproject.ui.view.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(mainLooper).postDelayed({ verificaLogin() },3000)
        changeStatusBar()
    }

    private fun verificaLogin() {
        auth = Firebase.auth
        if(auth?.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        finish()
    }
    private fun changeStatusBar() {
        window.statusBarColor = getColor(R.color.black)
    }
}