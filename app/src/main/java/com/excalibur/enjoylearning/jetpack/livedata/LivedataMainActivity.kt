package com.excalibur.enjoylearning.jetpack.livedata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.R

class LivedataMainActivity: AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata_main)
        textView = findViewById(R.id.activity_livedata_text)
        MyLivedata.data.observe(this){
            Log.d("TestForCase", "LivedataMainActivity observe change:$it")
            textView.text = it?:"empty"
        }

//        NoStickyLivedataBus.with("data", String::class.java, isStick = false).observe(this){
//            Log.d("TestForCase", "LivedataMainActivity observe change:$it")
//            textView.text = it?:"empty"
//        }

    }

    var count = 0;
    fun setValue(v: View){
        MyLivedata.data.value = "${System.currentTimeMillis()}"
        count += 1
        if(count == 3){
            MyLivedata.data.observe(this){
                Log.d("TestForCase", "count observe change:$it")
            }
        }
//        NoStickyLivedataBus.with("data", String::class.java).value = "${System.currentTimeMillis()}"
    }

    fun jump(v: View){
        val intent = Intent(this, LivedataMainActivity1::class.java)
        startActivity(intent)
    }

}