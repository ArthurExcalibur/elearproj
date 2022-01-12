package com.idazoo.ioc_annotation_lib.annotation_commmon

import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnBaseCommon(val setCommonListener: String, val listenerClazz: KClass<*>, val listenerMethod: String)