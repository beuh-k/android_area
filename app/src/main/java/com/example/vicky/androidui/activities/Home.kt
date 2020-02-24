package com.example.vicky.androidui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button_services.setOnClickListener {
            startActivity(Intent(this@Home, HomeAuthentification::class.java))

        }
    }
}