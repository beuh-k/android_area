package com.example.vicky.androidui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vicky.androidui.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            Toast.makeText(this@MainActivity,"Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
        signUp.setOnClickListener{
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }
    }



}
