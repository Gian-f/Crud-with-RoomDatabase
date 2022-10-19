package com.br.baseproject.ui.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.br.baseproject.R
import com.br.baseproject.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView=binding.navView

        navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_conversas -> {
                    true
                }
                R.id.navigation_perfil -> {
                    true
                }
                else -> false
            }
        }
    }
}