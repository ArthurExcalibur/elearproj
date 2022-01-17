package com.excalibur.enjoylearning.jetpack.databinding.viewbinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.databinding.ActivityViewBindingMainBinding

class ViewBindingMainActivity: AppCompatActivity() {

    var vb: ActivityViewBindingMainBinding ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityViewBindingMainBinding.inflate(layoutInflater)
        setContentView(vb!!.root)

        vb?.view?.setOnClickListener {
            Toast.makeText(this@ViewBindingMainActivity, "hahaha", Toast.LENGTH_SHORT).show()
        }
    }

}