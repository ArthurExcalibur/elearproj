package com.idazoo.ioc_annotation_lib.annotation_commmon

import android.view.View

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@OnBaseCommon(
    setCommonListener = "setOnClickListener",
    listenerClazz = View.OnClickListener::class,
    listenerMethod = "onClick"
)
annotation class OnClickCommon(val resourceID: Int)
