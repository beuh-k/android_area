package com.example.vicky.androidui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import com.example.vicky.androidui.api.Api
import com.example.vicky.androidui.api.RetrofitClient
import com.example.vicky.androidui.model.DefaultResponseFacebook
import com.example.vicky.androidui.model.DefaultResponseSignin
import com.facebook.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public class SignInActivity() : AppCompatActivity() {

        var stock_token = ""
        val callback = CallbackManager.Factory.create()

        companion object {
            var stockFB = false
        }
        private val preferences = MyPreferences()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_in)
            val authButton = findViewById<LoginButton>(R.id.login_button_facebook)
            authButton.setReadPermissions(listOf("email", "public_profile"))
            login_button_facebook.registerCallback(callback, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    stock_token = "Bearer " + loginResult!!.accessToken.token
                    preferences.putData(this@SignInActivity, stock_token)
                    sendToken(stock_token)
                }
                override fun onCancel() {
                    Toast.makeText(applicationContext, "deconnexion", Toast.LENGTH_LONG).show()
                }
                override fun onError(exception: FacebookException) {
                    Log.d("sebastien", exception.toString())
                }
            })
            login.setOnClickListener {
                logUser()
            }
            signUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            callback.onActivityResult(requestCode, resultCode, data)
            super.onActivityResult(requestCode, resultCode, data)
        }

        fun sendToken(token: String) {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                                .addHeader(
                                        "Authorization",
                                        preferences.getData(this@SignInActivity)
                                )
                                .build()
                        chain.proceed(request)
                    }
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://lanoyade.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            val api = retrofit.create(Api::class.java)

            api.responseFacebook(stock_token)
                    .enqueue(object : Callback<DefaultResponseFacebook> {
                        override fun onFailure(call: Call<DefaultResponseFacebook>, t: Throwable) {
                            t.printStackTrace()
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                                call: Call<DefaultResponseFacebook>,
                                response: Response<DefaultResponseFacebook>
                        ) {
                            val body = response.body()
                            if (body != null && body.success == true) {
                                stockFB = body.success
                                preferences.putDataFacebook(
                                        this@SignInActivity,
                                        body.success,
                                        body.token
                                )
                                startActivity(Intent(this@SignInActivity, Home::class.java))
                            } else {
                                Toast.makeText(
                                        applicationContext,
                                        "Wrong username or password",
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    })
        }

        private fun logUser() {
            val username = username.text.toString().trim()
            val password = password.text.toString().trim()
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
                            if (response.body()?.success == true) {
                                val success = response.body()!!.success
                                preferences.putDataFacebook(
                                        this@SignInActivity,
                                        success,
                                        response.body()?.token
                                )
                                startActivity(Intent(this@SignInActivity, Home::class.java))
                            } else {
                                Toast.makeText(
                                        applicationContext,
                                        "Wrong username or password",
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    })
        }


    }


