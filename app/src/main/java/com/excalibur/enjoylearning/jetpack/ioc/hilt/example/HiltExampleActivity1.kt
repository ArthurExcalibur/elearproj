package com.excalibur.enjoylearning.jetpack.ioc.hilt.example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltExampleActivity1 : AppCompatActivity() {

    @Inject
    lateinit var entity: HiltEntity
    @Inject
    lateinit var entity1: HiltEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("TestForCase", entity.toString())
        Toast.makeText(this, entity.toString(), Toast.LENGTH_SHORT).show()
        Log.e("TestForCase", entity1.toString())
        Toast.makeText(this, entity1.toString(), Toast.LENGTH_SHORT).show()
    }

}