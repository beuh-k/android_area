package com.example.vicky.androidui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import com.example.vicky.androidui.activities.SignInActivity.Companion.stockFB
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_facebook_auth.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class LogInFacebookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_auth)

        val callback = CallbackManager.Factory.create()

        val authButton = findViewById<LoginButton>(R.id.auth_facebook)
        authButton.setReadPermissions(listOf("email", "public_profile"))
        auth_facebook.registerCallback(callback, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                startActivity(Intent(this@LogInFacebookActivity, HomeAuthentification::class.java))
            }
            override fun onCancel() {
                Log.d("cancel", "print debug")
                Toast.makeText(applicationContext, "deconnexion", Toast.LENGTH_LONG).show()
            }
            override fun onError(exception: FacebookException) {
                Log.d("sebastien", exception.toString())
            }
        })

    }
}