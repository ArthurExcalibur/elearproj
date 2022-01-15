package com.excalibur.enjoylearning.jetpack.lifecycle.example3

import android.app.Activity
import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity

// TODO lifecycle实现生命周期监听
class ObserverActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(ActivityObserver())
    }

}