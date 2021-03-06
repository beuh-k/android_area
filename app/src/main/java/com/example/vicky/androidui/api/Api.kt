package com.example.vicky.androidui.api

import com.example.vicky.androidui.model.DefaultResponse
import com.example.vicky.androidui.model.DefaultResponseFacebook
import com.example.vicky.androidui.model.DefaultResponseSignin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("api/signup")
    fun createUser(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("username") username: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/signin")
    fun logUser (
        @Field("username") name: String,
        @Field("password") password: String
    ): Call<DefaultResponseSignin>

    @FormUrlEncoded
    @POST("api/auth/facebook")
    fun responseFacebook (
            @Field("Authorization") name: String
    ): Call<DefaultResponseFacebook>
}