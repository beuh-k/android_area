package com.example.vicky.androidui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vicky.androidui.R
import com.example.vicky.androidui.activities.SignInActivity.Companion.stockFB
import com.facebook.CallbackManager

import java.util.*


class HomeAuthentification : AppCompatActivity() {
    val callback = CallbackManager.Factory.create()


    var androidVersionList = ArrayList<ServiceModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_authentification)
        addlist()

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = Adapter(androidVersionList, ::onItemClick)
        recyclerView.adapter = adapter
    }

    fun addlist() {
        var fb = "not connected"
        if (stockFB)
            fb = "You are connected!"
        else
            fb = "not connected"
        androidVersionList.add(ServiceModel(R.drawable.logo_facebook, "Facebook", fb))
        androidVersionList.add(ServiceModel(R.drawable.logo_spotify, "Spotify", "Not connected"))
        androidVersionList.add(ServiceModel(R.drawable.logo_office, "Office", "Not connected"))
    }

    @SuppressLint("WrongViewCast")
    fun onItemClick(codeName: String) {
        when (codeName) {
            "Facebook" -> {
                startActivity(Intent(this@HomeAuthentification, LogInFacebookActivity::class.java))
            }
            "Spotify" -> {
                Toast.makeText(applicationContext, "Spotify work in progress", Toast.LENGTH_LONG).show()
            }
            "Office" -> {
                Toast.makeText(applicationContext, "Office work in progress", Toast.LENGTH_LONG).show()
            }
        }
    }
}



