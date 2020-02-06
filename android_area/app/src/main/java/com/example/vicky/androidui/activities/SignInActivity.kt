package com.example.vicky.androidui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import com.example.vicky.androidui.api.RetrofitClient
import com.example.vicky.androidui.model.DefaultResponseSignin
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val login_facebook = login_button_facebook
        val callback = CallbackManager.Factory.create()
        val login_facebook = findViewById<View>(R.id.login_button_facebook) as LoginButton
        var stock_token = ""

        
       // login_facebook.setReadPermissions("email")
        // If using in a fragment

        // Callback registration
        login_facebook.registerCallback(callback, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) { // App code
                stock_token = loginResult!!.accessToken.token
                Log.d("test", stock_token)
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                //Toast.makeText(applicationContext, stock_token, Toast.LENGTH_LONG).show()

            }

            override fun onCancel() { // App code
            }

            override fun onError(exception: FacebookException) { // App code
            }
        })

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            callback.onActivityResult(requestCode, resultCode, data)
            super.onActivityResult(requestCode, resultCode, data)
        }

        val accessToken = AccessToken.getCurrentAccessToken()
        //val isLoggedIn = accessToken != null && !accessToken.isExpired
        //Toast.makeText(applicationContext, stock_token, Toast.LENGTH_LONG).show()

        Log.d("sebastien", stock_token)




        login.setOnClickListener {

            val username = username.text.toString().trim()
            val password = password.text.toString().trim()
           // Toast.makeText(this@MainActivity, "Please fill in all fields", Toast.LENGTH_SHORT)
               // .show()
            RetrofitClient.instance.logUser(username, password)
                    .enqueue(object : Callback<DefaultResponseSignin> {
                        override fun onFailure(call: Call<DefaultResponseSignin>, t: Throwable) {
                            t.printStackTrace()
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                                call: Call<DefaultResponseSignin>,
                                response: Response<DefaultResponseSignin>
                        ) {
                            Log.d("username", username)
                            Log.v("seb", "boolean " + response.body()?.success)

                            if (response.body()?.success == true) {
                                //Toast.makeText(applicationContext, response.body()?.token, Toast.LENGTH_LONG).show()
                                //Toast.makeText(applicationContext, "Bienvenu", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))

                                //startActivity(Intent(this@SignInActivity, SignInActivity::class.java))
                            }
                            else {
                                Toast.makeText(applicationContext, "Wrong username or passwword", Toast.LENGTH_LONG).show()
                            }
                        }
                    })
        }

        signUp.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }


    }
}