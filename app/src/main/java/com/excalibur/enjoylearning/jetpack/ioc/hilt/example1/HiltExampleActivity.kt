package com.excalibur.enjoylearning.jetpack.ioc.hilt.example1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltExampleActivity : AppCompatActivity() {

    @Inject
    lateinit var hiltInterface: HiltInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hiltInterface.method()
    }

}