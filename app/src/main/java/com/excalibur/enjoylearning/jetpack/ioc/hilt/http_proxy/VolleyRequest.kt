package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

class VolleyRequest : IHttpRequest {
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