package com.excalibur.enjoylearning.vm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.R

class HotfixActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HotFixClass().toastError()
    }

}