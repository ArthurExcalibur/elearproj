package com.idazoo.ioc_annotation_lib.annotation_commmon

import com.idazoo.ioc_annotation_lib.annotation.InjectTool
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class CommonInjectTool {

    companion object{
        fun injectCommon(any: Any){
            val clazz: Class<*> = any.javaClass
            val mMethods: Array<Method>? = clazz.declaredMethods
            //TODO 遍历方法
            mMethods?.forEach {
                val annotations: Array<Annotation> = it.annotations
                //TODO 遍历方法上的注解
                annotations.forEach { methodAnnotation ->
                    //TODO 查找方法上的每个注解是否被OnBaseCommon修饰（OnBaseCommon子注解）
                    val annotationType: Class<out Annotation> = methodAnnotation.annotationClass.javaObjectType
                    val onBaseCommon = annotationType.getAnnotation(OnBaseCommon::class.java)
                    onBaseCommon?.let { base->
                        val setListener = base.setCommonListener
                        val listenerClazz = base.listenerClazz
                        val listenerMethod = base.listenerMethod

                        //TODO 因为获取到的是OnBaseCommon类型的，获取不到resourceID，所以用反射获取（这里反射的是methodAnnotation而不是onBaseCommon）
                        val resourceIDMethod : Method? = InjectTool.findMethod(methodAnnotation, "resourceID")
                        val viewId = resourceIDMethod?.invoke(methodAnnotation) as Int

                        //TODO 反射调用findViewById找到view
                        val method: Method? = InjectTool.findMethod(any, "findViewById", Int::class.java)
                        val view = method?.invoke(any, viewId)
                        view?.let {v->
                            val setListenerMethod: Method? = InjectTool.findMethod(v, setListener, listenerClazz.java)
                            val proxy = Proxy.newProxyInstance(v.javaClass.classLoader,arrayOf(listenerClazz.java)) { _, _, _ ->
                                //TODO ContentViewActivity中需要根据事件设置返回值（showBtn1是onClick没有返回值，showBtn2是onLongClick返回bool）
                                it.invoke(any)
                            }
                            //TODO 动态代理
                            setListenerMethod?.invoke(v, proxy)
                        }
                    }
                }
            }
        }
    }

}