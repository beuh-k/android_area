package com.example.vicky.androidui.api

import android.content.Context
import android.util.Log
import com.example.vicky.androidui.activities.MyPreferences
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitAuthentificationClient {

    private val preferences = MyPreferences()



    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY)
        })


    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
                .addHeader(
                        "Authorization",
                        "Bearer EAAKyJ0v1c1YBAFapWrwy6ORckkxqtZCd7s8EXTp3H5rDXGAvNcnuHS7mUTrvjYfgycRXuvIZCwNwTiZAOoeF8wv3VZB8R48GNycZA2UtW8NBk1YWWlC14PLuuhxH9f0GFgusRoQmM2ZCZCygmPUUijgN8N9iQTQghLxb86bFEqK4VV1ungtN0rfsKcQoOXj1iAjavKPkmfSgPJoMeCkIa3VQlD6RePMGcYZD"
                )
                .build()
        chain.proceed(request)
    }
    .build()

    val instance_auth: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://lanoyade.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        retrofit.create(Api::class.java)
    }
}


