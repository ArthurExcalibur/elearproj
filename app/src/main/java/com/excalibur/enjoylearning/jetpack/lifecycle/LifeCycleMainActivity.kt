package com.excalibur.enjoylearning.jetpack.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LifeCycleMainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TestForCase", "LifeCycleMainActivity onCreate1")
        lifecycle.addObserver(object : DefaultLifecycleObserver{
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onCreate")
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onStart")
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onResume")
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onPause")
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onStop")
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                Log.e("TestForCase", "DefaultLifecycleObserver onDestroy")
            }
        })
        Log.e("TestForCase", "LifeCycleMainActivity onCreate2")
    }

    override fun onStart() {
        super.onStart()
        Log.e("TestForCase", "LifeCycleMainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("TestForCase", "LifeCycleMainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("TestForCase", "LifeCycleMainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("TestForCase", "LifeCycleMainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TestForCase", "LifeCycleMainActivity onDestroy")
    }

}