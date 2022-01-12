package com.idazoo.ioc_annotation_lib.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnClick(val resourceID: Int)