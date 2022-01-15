package com.excalibur.enjoylearning.jetpack.lifecycle.example2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// TODO MVP 的 P层实现生命周期监听
class LifeCyclePresenterActivity : AppCompatActivity() {

    var lifeCyclePresenter: LifeCyclePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifeCyclePresenter = LifeCyclePresenter()
    }

    override fun onResume() {
        super.onResume()
        lifeCyclePresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        lifeCyclePresenter?.onPause()
    }

}