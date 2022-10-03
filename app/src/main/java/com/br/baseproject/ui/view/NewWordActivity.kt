package com.br.baseproject.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.br.baseproject.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        iniciaToolbar()
    }

    private fun iniciaToolbar() {
        val toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        this.binding.btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(this.binding.edtWord.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = this.binding.edtWord.text.toString()
                replyIntent.putExtra("REPLY", word)
                setResult(Activity.RESULT_OK, replyIntent)
                this.binding.progressBar.visibility = View.VISIBLE
            }
            finish()
        }
    }
}