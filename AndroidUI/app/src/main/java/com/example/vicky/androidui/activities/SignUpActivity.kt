package com.example.vicky.androidui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.vicky.androidui.Model.DefaultResponse
import com.example.vicky.androidui.R
import com.example.vicky.androidui.api.RetrofitClient
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        validate.setOnClickListener{
            val name = email__signUp.text.toString().trim()
            val password = password__signUp.text.toString().trim()
            val verif_password = password2__signUp.text.toString().trim()
            val username = username_signUp.text.toString().trim()

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

            if (name.isEmpty()) {
                email__signUp.error = "email required"
                email__signUp.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
                email__signUp.error = "valid email required"
                email__signUp.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createuser(name, password, username).enqueue(object:Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("error", t.message)

                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    Log.d("seb", "debiug numero 1")
                    //Log.d("seb", response.body()?.msg)
                    Log.v("seb", "boolean = " + response.body()?.success);
                    Log.d("seb", name)
                    /*if (response.body()?.msg == "Successful created new user.") {
                        Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                    }
                    else {
                        Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_LONG).show()
                    }*/
                }

            })
        }
    }
}
