package com.excalibur.enjoylearning.jetpack.lifecycle.example3

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ActivityObserver1: DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("TestForCase", "ActivityObserver1 onResume...")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("TestForCase", "ActivityObserver1 onPause...")
    }

}