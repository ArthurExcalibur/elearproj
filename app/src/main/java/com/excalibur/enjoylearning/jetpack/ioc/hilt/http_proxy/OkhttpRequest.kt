package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

class OkhttpRequest : IHttpRequest {
    override fun postHttp(url: String, params: Map<String, Any>, callback: ICallback) {
        callback.onSuccess("{\n" +
                "\t\"name\": \"okhttp\",\n" +
                "\t\"method\": \"post\",\n" +
                "\t\"data\": \"success\"\n" +
                "}")
    }

    override fun getHttp(url: String, callback: ICallback) {
        callback.onFailure("{\n" +
                "\t\"name\": \"okhttp\",\n" +
                "\t\"method\": \"get\",\n" +
                "\t\"data\": \"fail\"\n" +
                "}")
    }
}