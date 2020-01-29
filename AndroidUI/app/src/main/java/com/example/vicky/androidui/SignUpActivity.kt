package com.example.vicky.androidui

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.vicky.androidui.AsyncTask.SignUpRequest
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        validate.setOnClickListener {

            val email = email__signUp.text.toString().trim()
            val password = password__signUp.text.toString().trim()
            val verif_password = password2__signUp.text.toString().trim()
            val username = username_signUp.text.toString().trim()
            val resulmsg = ""

            if (username.isEmpty()) {
                username_signUp.error = "username required"
                username_signUp.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                password__signUp.error = "6 char password required"
                password__signUp.requestFocus()
                return@setOnClickListener
            }

            if (verif_password.isEmpty() || verif_password != password) {
                password2__signUp.error = "wrong password"
                password2__signUp.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                email__signUp.error = "email required"
                email__signUp.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email__signUp.error = "valid email required"
                email__signUp.requestFocus()
                return@setOnClickListener
            }

            if (username_signUp.text.toString().isNotEmpty() && password__signUp.text.toString().isNotEmpty() && password2__signUp.text.toString().isNotEmpty() && email__signUp.text.toString().isNotEmpty()) {
                SignUpRequest(username_signUp.text.toString(), password__signUp.text.toString(), email__signUp.text.toString()).execute()
            }
        }
        //else
        //  Toast.makeText(this@SignUpActivity,"Please fill in all fields", Toast.LENGTH_SHORT).show()
    }
}
