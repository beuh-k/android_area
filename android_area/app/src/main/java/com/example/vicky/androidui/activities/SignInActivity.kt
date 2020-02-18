package com.example.vicky.androidui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import com.example.vicky.androidui.api.RetrofitClient
import com.example.vicky.androidui.model.DefaultResponseFacebook
import com.example.vicky.androidui.model.DefaultResponseSignin
import com.facebook.*
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.log


public class SignInActivity() : AppCompatActivity() {

    val callback = CallbackManager.Factory.create()

    private val preferences = MyPreferences()
    //private val stack = preferences.getData(this)

    //val data_sharespreferencies = getSharedPreferences("STOCK_DATA", Context.MODE_PRIVATE)
    //val editor = data_sharespreferencies.edit()


    companion object {
        var stock_token = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val authButton = findViewById(R.id.login_button_facebook) as LoginButton
        authButton.setReadPermissions(Arrays.asList("email", "public_profile"))

        login_button_facebook.registerCallback(callback, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                stock_token = "Bearer " + loginResult!!.accessToken.token
                preferences.putData(this@SignInActivity, loginResult!!.accessToken.token)
                        RetrofitClient.instance.responseFacebook(stock_token)
                        .enqueue(object : Callback<DefaultResponseFacebook> {

                            override fun onFailure(call: Call<DefaultResponseFacebook>, t: Throwable) {
                                t.printStackTrace()
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            }
                            override fun onResponse(
                                    call: Call<DefaultResponseFacebook>,
                                    response: Response<DefaultResponseFacebook>
                            ) {

                                if (response.body()?.success == true) {
                                   // MainActivity.global_.token_ = response.body()?.token
                                    //edit.putString(response.body()?.token)
                                  //  startActivity(Intent(this@SignInActivity, HomeMenuActivity::class.java))
                                }
                                else {
                                    Toast.makeText(applicationContext, "Wrong username or password", Toast.LENGTH_LONG).show()
                                 //   startActivity(Intent(this@SignInActivity, HomeMenuActivity::class.java))
                                }
                            }
                        })
            }
            override fun onCancel() { // App code
            }
            override fun onError(exception: FacebookException) {
                Log.d("sebastien", exception.toString())
            }
        })


        //val accessToken = AccessToken.getCurrentAccessToken()

        //val isLoggedIn = accessToken != null && !accessToken.isExpired

        //Log.d("seb", accessToken.token)

        //val accessToken = AccessToken.getCurrentAccessToken()

        login.setOnClickListener {



            val username = username.text.toString().trim()
            val password = password.text.toString().trim()


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
                            if (response.body()?.success == true) {
                                //MainActivity.global_.email_ = username
                                //MainActivity.global_.token_ = response.body()?.token


                                startActivity(Intent(this@SignInActivity, HomeMenuActivity::class.java))
                            }
                            else {
                                Toast.makeText(applicationContext, "Wrong username or password", Toast.LENGTH_LONG).show()
                            }
                        }
                    })
        }
        signUp.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callback.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

