package com.idazoo.ioc_annotation_lib.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val resourceID: Int)