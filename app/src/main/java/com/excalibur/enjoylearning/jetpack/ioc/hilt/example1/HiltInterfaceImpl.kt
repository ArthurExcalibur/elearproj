package com.excalibur.enjoylearning.jetpack.ioc.hilt.example1

import android.util.Log
import javax.inject.Inject

class HiltInterfaceImpl @Inject constructor(): HiltInterface {

    override fun method() {
        Log.e("TestForCase", "HiltInterfaceImpl method...")
    }
}