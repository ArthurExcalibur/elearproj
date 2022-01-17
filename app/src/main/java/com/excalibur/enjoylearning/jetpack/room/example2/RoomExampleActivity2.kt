package com.excalibur.enjoylearning.jetpack.room.example2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class RoomExampleActivity2: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        viewModel.queryAll()?.observe(this){
            //...
        }
    }

}