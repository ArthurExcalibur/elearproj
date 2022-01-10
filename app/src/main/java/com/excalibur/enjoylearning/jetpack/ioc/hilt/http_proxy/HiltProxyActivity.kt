package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class HiltProxyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HttpHelper.instance.getHttp("", object : HttpCallback<HiltDataEntity>() {
            override fun onHttpSuccess(t: HiltDataEntity) {
                Log.e("TestForCase", "$t")
            }

            override fun onHttpFailure(result: String) {
                Log.e("TestForCase", result)
            }
        })

        var map: Map<String, String> = mutableMapOf()
        HttpHelper.instance.postHttp("", map, object : HttpCallback<HiltDataEntity>() {
            override fun onHttpSuccess(t: HiltDataEntity) {
                Log.e("TestForCase", "$t")
            }

            override fun onHttpFailure(result: String) {
                Log.e("TestForCase", result)
            }
        })
    }

}