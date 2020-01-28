package com.example.vicky.androidui.AsyncTask

import android.content.Intent
import android.os.AsyncTask
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.example.vicky.androidui.MainActivity
import okhttp3.*
import org.json.JSONObject


class SignUpRequest(
        var username: String,
        var password: String,
        var name: String
): AsyncTask<Void, Void, String?>() {

    override fun doInBackground(vararg params: Void?): String? {

        val okHttpClient = OkHttpClient()
        Log.d("sebastien", "1ere etape")

        val formBody = FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("name", name)
                .build()
        Log.d("sebastien", "2eme etape")

        val request = Request.Builder()
                .url("http://192.168.43.66:3000/api/signup")
                .post(formBody)
                .build()
        Log.d("sebastien", "3eme etape")


        val response = okHttpClient.newCall(request).execute()
        Log.d("sebastien", response.body()?.string())

        return response.body()?.string()
    }

    override fun onPostExecute(result: String?) {
        if (result != null) {
            val obj = JSONObject(result)
            val resultmsg = obj.getString("msg")
            val resultsucces = obj.getBoolean("success")

            Log.d("sebastien", resultmsg)
            Log.d("sebastien", "" + resultsucces)

        } else {
            Log.d("sebastien", "error")

        }
        super.onPostExecute(result)
    }
}

class SignUpResponse(
        var message: String,
        var success: Boolean
)