package com.excalibur.enjoylearning.jetpack.lifecycle.example3

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ActivityObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onActivityResume() = Log.e("TestForCase", "ActivityObserver onActivityResume...")

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onActivityPause() = Log.e("TestForCase", "ActivityObserver onActivityPause...")

}