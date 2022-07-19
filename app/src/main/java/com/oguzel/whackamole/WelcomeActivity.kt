package com.oguzel.whackamole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val intent2 = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(intent2)
        }
    }
}