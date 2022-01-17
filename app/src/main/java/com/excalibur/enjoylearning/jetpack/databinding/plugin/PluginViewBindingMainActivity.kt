package com.excalibur.enjoylearning.jetpack.databinding.plugin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.R
import kotlinx.android.synthetic.main.activity_view_binding_main.*

class PluginViewBindingMainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_binding_main)

        view.setOnClickListener {
            Toast.makeText(this@PluginViewBindingMainActivity, "hahaha", Toast.LENGTH_SHORT).show()
        }
    }

}