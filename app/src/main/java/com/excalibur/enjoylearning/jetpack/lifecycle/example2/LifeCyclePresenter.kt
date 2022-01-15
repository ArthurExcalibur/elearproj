package com.excalibur.enjoylearning.jetpack.lifecycle.example2

import android.util.Log

class LifeCyclePresenter {

    fun onResume() = Log.d("TestForCase", "onResume run ...")

    fun onPause() = Log.d("TestForCase", "onPause run ...")

}