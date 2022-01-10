package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt

import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.ICallback
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.IHttpRequest
import javax.inject.Inject

class VolleyHiltRequest @Inject constructor(): IHttpRequest {
    override fun postHttp(url: String, params: Map<String, Any>, callback: ICallback) {
        callback.onSuccess("{\n" +
                "\t\"name\": \"Volley\",\n" +
                "\t\"method\": \"post\",\n" +
                "\t\"data\": \"success\"\n" +
                "}")
    }

    override fun getHttp(url: String, callback: ICallback) {
        callback.onFailure("{\n" +
                "\t\"name\": \"Volley\",\n" +
                "\t\"method\": \"get\",\n" +
                "\t\"data\": \"fail\"\n" +
                "}")
    }
}