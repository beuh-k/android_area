package com.example.vicky.androidui.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    //private const val BASE_URL = "http://ec2-34-234-166-115.compute-1.amazonaws.com/"
    //private const val BASE_URL = "http://192.168.43.66:3000/"
   // private const val BASE_URL = "http://34.234.166.115/"
    private const val BASE_URL = "https://lanoyade.com/"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY)
        })


    .addInterceptor { chain ->
        val original = chain.request()
        Log.d("debug", original.toString())
        var string = original.toString()
       // string = string.replace("%26", "&")
       // string = string.replace("%3D", "=")*/


        val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
        val request = requestBuilder.build()
            chain.proceed(request)
    }
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}