package com.example.vicky.androidui.activities

import android.content.Context
import android.provider.Settings.Global.getString

class MyPreferences {
    fun putData(context: Context, token: String) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                ?.let { preferences ->
                    with(preferences.edit()) {
                        putString(FACEBOOK_TOKEN, token)
                        commit()
                    }
                }
    }

    fun putDataFacebook(context: Context, successFb: Boolean, token: String?) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                ?.let { preferences ->
                    with(preferences.edit()) {
                        putString("token serv fb", token)
                        putBoolean("succes serv", successFb)
                        commit()
                    }
                }

    }

    fun getData(context: Context): String {
        var retrieveToken = ""
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                ?.let { preferences ->
                    retrieveToken = preferences.getString(FACEBOOK_TOKEN, null)
                }
        return retrieveToken
    }

    companion object {
        private const val PREFERENCES_NAME = "MyPreferences"
        private const val FACEBOOK_TOKEN = "Facebook_token"
    }

}