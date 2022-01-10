package com.excalibur.enjoylearning.jetpack.ioc.hilt.http_hilt

import com.excalibur.enjoylearning.jetpack.ioc.hilt.http_proxy.IHttpRequest
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class HttpHiltHelperModule {

    @BindVolley
    @Binds
    abstract fun bindVolleyLib(volleyRequest: VolleyHiltRequest) : IHttpRequest

//    @BindXUtils
//    @Binds
//    abstract fun bindXUtilsLib(xUtilsRequest: XUtilsHiltRequest) : IHttpRequest
//
//    @BindOkhttp
//    @Binds
//    abstract fun bindOkhttpLib(okhttpRequest: OkhttpHiltRequest) : IHttpRequest

}