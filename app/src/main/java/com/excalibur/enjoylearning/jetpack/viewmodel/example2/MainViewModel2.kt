package com.excalibur.enjoylearning.jetpack.viewmodel.example2

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel2(application: Application): AndroidViewModel(application) {

    val phoneNumber by lazy { MutableLiveData<String>() }

    init {
        phoneNumber.value = ""
    }

    private val context: Context = application

    fun appendNumber(number: String){
        phoneNumber.value += number
    }

    fun clear(){
        phoneNumber.value = ""
    }

    fun call(){
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:${phoneNumber.value}")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun back(){
        val length = phoneNumber.value?.length?:0
        if(length >= 1){
            phoneNumber.value = phoneNumber.value!!.substring(0, length - 1)
        }
    }

}