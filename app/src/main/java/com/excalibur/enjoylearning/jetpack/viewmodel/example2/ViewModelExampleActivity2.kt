package com.excalibur.enjoylearning.jetpack.viewmodel.example2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.excalibur.enjoylearning.R
import com.excalibur.enjoylearning.databinding.ActivityViewModelExaple2Binding

class ViewModelExampleActivity2: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityViewModelExaple2Binding>(this, R.layout.activity_view_model_exaple2)
        //ViewModelProvider(this).get(MainViewModel2::class.java)缩写
        val viewModel = ViewModelProvider(this)[MainViewModel2::class.java]
        dataBinding.model = viewModel
        dataBinding.lifecycleOwner = this

        /**
         * ViewModelProvider每次生成的都是不同的对象（之前还以为是单例来的。。。。）
         * MainViewModel同个activity中获取的都是同一份（因为getViewModelStore是同一份？），不同activity中不是
         *
         * ViewModelExampleActivity2:
         * androidx.lifecycle.ViewModelProvider@5c62cb1
         * com.excalibur.enjoylearning.jetpack.viewmodel.example2.MainViewModel2@8e52596
         * androidx.lifecycle.ViewModelProvider@308e517
         * com.excalibur.enjoylearning.jetpack.viewmodel.example2.MainViewModel2@8e52596
         *
         * ViewModelExampleActivity3:
         * androidx.lifecycle.ViewModelProvider@961de0b
         * com.excalibur.enjoylearning.jetpack.viewmodel.example2.MainViewModel2@588c701
         */
        val provider = ViewModelProvider(this)
        Log.e("TestForCase", provider.toString())
        Log.e("TestForCase", provider[MainViewModel2::class.java].toString())

        val provider1 = ViewModelProvider(this)
        Log.e("TestForCase",provider1.toString())
        Log.e("TestForCase", provider1[MainViewModel2::class.java].toString())

        val intent = Intent(this, ViewModelExampleActivity3::class.java)
        startActivity(intent)
    }

}