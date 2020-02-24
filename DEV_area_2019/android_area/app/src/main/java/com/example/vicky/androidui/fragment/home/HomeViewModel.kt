package com.example.vicky.androidui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicky.androidui.activities.MyPreferences

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fragment"
    }
    val text: LiveData<String> = _text
}