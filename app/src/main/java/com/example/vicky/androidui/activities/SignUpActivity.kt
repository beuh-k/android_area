package com.example.vicky.androidui.activities

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.androidui.R
import com.example.vicky.androidui.api.RetrofitClient
import com.example.vicky.androidui.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_sign_up.emailField
import kotlinx.android.synthetic.main.activity_sign_up.passwordConfirmField
import kotlinx.android.synthetic.main.activity_sign_up.passwordField
import kotlinx.android.synthetic.main.activity_sign_up.userNameField
import kotlinx.android.synthetic.main.activity_sign_up.validateButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        validateButton.setOnClickListener {
            val username = userNameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = passwordConfirmField.text.toString().trim()

            if (username.isEmpty()) {
                userNameField.error = "username required"
                userNameField.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                passwordField.error = "6 char password required"
                passwordField.requestFocus()
                return@setOnClickListener
            }
            if (confirmPassword.isEmpty() || confirmPassword != password) {
                passwordConfirmField.error = "wrong password"
                passwordConfirmField.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                emailField.error = "email required"
                emailField.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailField.error = "valid email required"
                emailField.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.createUser(username, password, email)
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Log.d("seb", "debiug numero 1")
                        Log.v("seb", "boolean = " + response.body()?.success);
                        Log.d("seb", email)
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
