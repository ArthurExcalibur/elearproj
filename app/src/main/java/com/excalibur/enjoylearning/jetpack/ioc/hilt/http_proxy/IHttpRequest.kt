package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

interface IHttpRequest {

    fun postHttp(url: String, params: Map<String, Any>, callback: ICallback)

    fun getHttp(url: String, callback: ICallback)

}