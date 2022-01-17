package com.excalibur.enjoylearning.jetpack.viewmodel.example2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ViewModelExampleActivity3: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val provider = ViewModelProvider(this)
        Log.e("TestForCase", provider.toString())
        Log.e("TestForCase", provider[MainViewModel2::class.java].toString())
    }

}