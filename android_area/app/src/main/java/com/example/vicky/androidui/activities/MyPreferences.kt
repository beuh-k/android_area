package com.example.vicky.androidui.activities

import android.content.Context
import android.provider.Settings.Global.getString

class MyPreferences {

    //val stock_token = ""

    //val preferences = context.getSharedPreferences(stock_token, Context.MODE_PRIVATE)


    fun putData(context: Context, token: String) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                ?.let { preferences ->
                    with(preferences.edit()) {
                        putString(FACEBOOK_TOKEN, token)
                        commit()
                    }
                }
    }

    fun getData(context: Context): String? {
        var retrieveToken: String? = null
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