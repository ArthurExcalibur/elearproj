package com.excalibur.enjoylearning.jetpack.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.idazoo.ioc_annotation_lib.annotation.InjectTool
import java.lang.reflect.Field

class LivedataMainActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mVersionFiled: Field? = InjectTool.getFiled(MyLivedata.data, "mVersion")
        mVersionFiled?.set(MyLivedata.data, -1)

//        MyLivedata.data.observe(this){
//            Log.d("TestForCase", "LivedataMainActivity1 observe change:$it")
//        }

        NoStickyLivedataBus.with("data", String::class.java, isStick = false).observe(this){
            Log.d("TestForCase", "LivedataMainActivity observe change:$it")
        }
    }

}