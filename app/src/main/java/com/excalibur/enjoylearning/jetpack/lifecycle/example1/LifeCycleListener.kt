package com.excalibur.enjoylearning.jetpack.lifecycle.example1

import android.util.Log

class LifeCycleListener {

    fun onResume() = Log.e("TestForCase", "onResume...")
    fun onPause() = Log.e("TestForCase", "onPause...")

}