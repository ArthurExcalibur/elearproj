package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt

import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.ICallback
import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.IHttpRequest
import javax.inject.Inject

class XUtilsHiltRequest @Inject constructor(): IHttpRequest {
    override fun postHttp(url: String, params: Map<String, Any>, callback: ICallback) {
        callback.onSuccess("{\n" +
                "\t\"name\": \"xutls\",\n" +
                "\t\"method\": \"post\",\n" +
                "\t\"data\": \"success\"\n" +
                "}")
    }

    override fun getHttp(url: String, callback: ICallback) {
        callback.onFailure("{\n" +
                "\t\"name\": \"xutls\",\n" +
                "\t\"method\": \"get\",\n" +
                "\t\"data\": \"fail\"\n" +
                "}")
    }
}