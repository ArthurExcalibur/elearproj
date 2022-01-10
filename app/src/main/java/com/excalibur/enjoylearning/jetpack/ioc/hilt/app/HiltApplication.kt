package com.excalibur.enjoylearning.jetpack.ioc.hilt.app

import android.app.Application
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt.BindOkhttp
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt.BindVolley
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt.BindXUtils
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.*
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.IHttpRequest




@HiltAndroidApp
class HiltApplication : Application(){

    /**
     * 通过hilt实现接口隔离
     */
//    @Inject
//    @BindVolley
//    lateinit var volleyRequest: IHttpRequest
//    @Inject
//    @BindXUtils
//    lateinit var xUtilsRequest: IHttpRequest
//    @Inject
//    @BindOkhttp
//    lateinit var okhttpRequest: IHttpRequest

    override fun onCreate() {
        super.onCreate()
        initNetworkLibProxy()
    }

    /**
     * 通过代理的方式实现接口隔离
     */
    private fun initNetworkLibProxy(){
//        HttpHelper.instance.initRequest(VolleyRequest())
//        HttpHelper.instance.initRequest(XUtilsRequest())
        HttpHelper.instance.initRequest(OkhttpRequest())
    }

}