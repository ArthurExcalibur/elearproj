package com.idazoo.ioc_annotation_lib.annotation

import androidx.annotation.LayoutRes

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContentView(@LayoutRes val layoutResID: Int)