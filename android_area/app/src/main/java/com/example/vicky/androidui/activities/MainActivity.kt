package com.example.vicky.androidui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R

class MainActivity: AppCompatActivity() {

    class global_ {
        companion object {
            var username_: String = ""
            var email_: String = ""
            var token_: String? = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}