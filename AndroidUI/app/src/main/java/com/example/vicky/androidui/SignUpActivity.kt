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
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.*
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        validate.setOnClickListener{


            if (username_signUp.text.toString().isNotEmpty() && password__signUp.text.toString().isNotEmpty() && password2__signUp.text.toString().isNotEmpty() && email__signUp.text.toString().isNotEmpty()) {
                makePostRequst(this@SignUpActivity, username_signUp.text.toString(), password__signUp.text.toString(), email__signUp.text.toString()).execute()
                startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            }

            else
                Toast.makeText(this@SignUpActivity,"Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    class makePostRequst(var activity: SignUpActivity, var username: String, var password: String, var name: String) : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            val client = OkHttpClient()
            val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", username)
                    .addFormDataPart("password", password)
                    .addFormDataPart("name", name)
                    .build()
            val request = Request.Builder()
                    .url("http://3000/api/signup")
                    .post(requestBody)
                    .build()
            val response = client.newCall(request).execute()
            return response.body()!!.string()
        }

        override fun onPostExecute(result: String?) {
            if (result != null) {
                val obj = JSONObject(result)
                postResult = obj.getString("message")
                makeJSONRequst(activity, username, password, name).execute()
            }
            super.onPostExecute(result)
        }

    }

    class makeJSONRequst(var activity: SignUpActivity, var username: String, var password: String, var name: String) : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            val JSON = MediaType.parse("application/json; charset=utf-8")
            val client = OkHttpClient()
            val requestObject = com.example.vicky.androidui.Model.Request()
            requestObject.username = username
            requestObject.password = password
            requestObject.name = name
            val body = RequestBody.create(JSON, Gson().toJson(requestObject))
            val request = Request.Builder()
                    .url("http://3000/api/signup")
                    .post(body)
                    .build()
            val response = client.newCall(request).execute()
            return response.body()!!.string()
        }

        override fun onPostExecute(result: String?) {
            if (result != null) {
                val obj = JSONObject(result)
                jsonResult = obj.getString("message")
            }
            val dialog = AlertDialog.Builder(activity)
            val view = activity.layoutInflater.inflate(R.layout.dialog_result, null)
            dialog.setView(view)
            view.findViewById<TextView>(R.id.json_result).text = jsonResult
            view.findViewById<TextView>(R.id.post_result).text = postResult
            dialog.show()
            super.onPostExecute(result)
        }
    }


}
