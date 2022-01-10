package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

class HttpHelper private constructor(){

    companion object{
        val instance: HttpHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpHelper()
        }
    }

    lateinit var request: IHttpRequest
    fun initRequest(request: IHttpRequest){
        this.request = request
    }

    fun getHttp(url: String, callback: ICallback) = request.getHttp(url, callback)
    fun postHttp(url: String, params: Map<String, Any>, callback: ICallback) = request.postHttp(url, params, callback)

}