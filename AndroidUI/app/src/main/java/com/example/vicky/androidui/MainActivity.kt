package com.example.vicky.androidui

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.OkHttpClient
import org.json.JSONObject
import okhttp3.RequestBody


var jsonResult = ""
var postResult = ""

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
