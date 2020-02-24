package com.example.vicky.androidui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vicky.androidui.R
import com.example.vicky.androidui.activities.SignInActivity.Companion.stockFB

import kotlinx.android.synthetic.main.service_model.*
import java.util.*


class HomePage : AppCompatActivity() {

    var androidVersionList = ArrayList<ServiceModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
        addlist()


        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = Adapter(this, androidVersionList)
        recyclerView.adapter = adapter
    }

    fun addlist() {
        var fb = "not connected"
        if (stockFB == true)
            fb = "You are connected!"
        androidVersionList.add(ServiceModel(R.drawable.logo_facebook, "Facebook", fb, button_connect))
        androidVersionList.add(ServiceModel(R.drawable.logo_spotify, "Spotify", "Not connected", button_connect))
        androidVersionList.add(ServiceModel(R.drawable.logo_office, "Office", "Not connected", button_connect))

    }
}



