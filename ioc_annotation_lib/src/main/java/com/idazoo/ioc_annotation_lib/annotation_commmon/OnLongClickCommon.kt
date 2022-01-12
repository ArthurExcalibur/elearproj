package com.idazoo.ioc_annotation_lib.annotation_commmon

import android.view.View

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@OnBaseCommon(
    setCommonListener = "setOnLongClickListener",
    listenerClazz = View.OnLongClickListener::class,
    listenerMethod = "onLongClick"
)
annotation class OnLongClickCommon(val resourceID: Int)