package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

//TODO 泛型T代表数据bean
abstract class HttpCallback<T> : ICallback {
    override fun onSuccess(result: String) {
        val clz = analysisClassInfo(this)
        onHttpSuccess(Gson().fromJson(result, clz) as T)
    }

    abstract fun onHttpSuccess(t : T)

    override fun onFailure(result: String) {
        onHttpFailure(result)
    }

    abstract fun onHttpFailure(result: String)

    private fun analysisClassInfo(any: Any): Class<*> {
        val getType: Type? = any.javaClass.genericSuperclass
        val params: Array<Type> = (getType as ParameterizedType).actualTypeArguments
        return params[0] as Class<*>
    }
}