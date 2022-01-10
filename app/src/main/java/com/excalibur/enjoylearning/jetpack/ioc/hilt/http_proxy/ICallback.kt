package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

interface ICallback {

    fun onSuccess(result: String)
    fun onFailure(result: String)

}