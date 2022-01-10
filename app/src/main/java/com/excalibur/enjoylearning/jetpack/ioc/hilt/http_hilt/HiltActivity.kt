package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.jetpack.ioc.hilt.app.HiltApplication
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.HiltDataEntity
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.HttpCallback
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.IHttpRequest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    @Inject
    @BindVolley
    lateinit var volleyRequest: IHttpRequest
    @Inject
    @BindXUtils
    lateinit var xUtilsRequest: IHttpRequest
    @Inject
    @BindOkhttp
    lateinit var okhttpRequest: IHttpRequest
//    var volleyRequest: IHttpRequest? = null
//    var xUtilsRequest: IHttpRequest? = null
//    var okhttpRequest: IHttpRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var map: Map<String, String> = mutableMapOf()
//        this.volleyRequest = (application as HiltApplication).volleyRequest
//        this.xUtilsRequest = (application as HiltApplication).xUtilsRequest
//        this.okhttpRequest = (application as HiltApplication).okhttpRequest
        volleyRequest?.getHttp("", object : HttpCallback<HiltDataEntity>() {
            override fun onHttpSuccess(t: HiltDataEntity) {
                Log.e("TestForCase", "$t")
            }

            override fun onHttpFailure(result: String) {
                Log.e("TestForCase", result)
            }
        })
        volleyRequest?.postHttp("", map, object : HttpCallback<HiltDataEntity>() {
            override fun onHttpSuccess(t: HiltDataEntity) {
                Log.e("TestForCase", "$t")
            }

            override fun onHttpFailure(result: String) {
                Log.e("TestForCase", result)
            }
        })

//        xUtilsRequest?.getHttp("", object : HttpCallback<HiltDataEntity>() {
//            override fun onHttpSuccess(t: HiltDataEntity) {
//                Log.e("TestForCase", "$t")
//            }
//
//            override fun onHttpFailure(result: String) {
//                Log.e("TestForCase", result)
//            }
//        })
//        xUtilsRequest?.postHttp("", map, object : HttpCallback<HiltDataEntity>() {
//            override fun onHttpSuccess(t: HiltDataEntity) {
//                Log.e("TestForCase", "$t")
//            }
//
//            override fun onHttpFailure(result: String) {
//                Log.e("TestForCase", result)
//            }
//        })
//
//        okhttpRequest?.getHttp("", object : HttpCallback<HiltDataEntity>() {
//            override fun onHttpSuccess(t: HiltDataEntity) {
//                Log.e("TestForCase", "$t")
//            }
//
//            override fun onHttpFailure(result: String) {
//                Log.e("TestForCase", result)
//            }
//        })
//        okhttpRequest?.postHttp("", map, object : HttpCallback<HiltDataEntity>() {
//            override fun onHttpSuccess(t: HiltDataEntity) {
//                Log.e("TestForCase", "$t")
//            }
//
//            override fun onHttpFailure(result: String) {
//                Log.e("TestForCase", result)
//            }
//        })
    }

}