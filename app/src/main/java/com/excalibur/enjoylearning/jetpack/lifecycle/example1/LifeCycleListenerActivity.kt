package com.excalibur.enjoylearning.jetpack.lifecycle.example1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// TODO 自定义接口实现生命周期监听
class LifeCycleListenerActivity : AppCompatActivity() {

    var lifeCycleListener: LifeCycleListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifeCycleListener = LifeCycleListener()
    }

    override fun onResume() {
        super.onResume()
        lifeCycleListener?.onResume()
    }

    override fun onPause() {
        super.onPause()
        lifeCycleListener?.onPause()
    }

}