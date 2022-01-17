package com.excalibur.enjoylearning.jetpack.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.excalibur.enjoylearning.R
import com.excalibur.enjoylearning.databinding.ActivityDatabindingMainBinding

class DataBindingMainActivity: AppCompatActivity() {

    private var entity = DataEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDatabindingMainBinding>(this, R.layout.activity_databinding_main)
        entity.name.set("zhangsah")
        entity.pass.set("11111")
        binding.data = entity

//        Thread {
//            run {
//                Thread.sleep(5000)
//                while (true){
//                    entity.name.set("${System.currentTimeMillis()}")
//                    entity.pass.set("${System.currentTimeMillis()}")
//                    Thread.sleep(1000)
//                }
//            }
//        }.start()
    }

}