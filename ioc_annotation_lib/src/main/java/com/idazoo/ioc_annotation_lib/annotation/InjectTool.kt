package com.idazoo.ioc_annotation_lib.annotation

import android.view.View
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

/**
 * 通过反射和注解实现布局绑定，控件发现，控件点击事件等功能
 */
class InjectTool {

    companion object{

        fun inject(any: Any){
            injectLayout(any)
            injectView(any)
            injectOnclick(any)
        }
        /**
         * 通过反射获取activity对象的setContentView方法，手动调用
         */
        private fun injectLayout(any: Any){
            val clazz: Class<*> = any.javaClass
            val mContentView: ContentView? = clazz.getAnnotation(ContentView::class.java)
            mContentView?.let { id ->
                val layoutId = id.layoutResID
                val method = findMethod(any, "setContentView", Int::class.java)
                method?.invoke(any, layoutId)
            }
        }

        private fun injectView(any: Any){
            val clazz: Class<*> = any.javaClass
            val mFields: Array<Field>? = clazz.declaredFields
            mFields?.forEach {
                it.isAccessible = true
                val bindView: BindView? = it.getAnnotation(BindView::class.java)
                bindView?.let {bind ->
                    val viewId = bind.resourceID
                    val method = findMethod(any, "findViewById", Int::class.java)
                    val view = method?.invoke(any, viewId)
                    it.set(any, view)
                }
            }
        }

        private fun injectOnclick(any: Any){
            val clazz: Class<*> = any.javaClass
            val mMethods: Array<Method>? = clazz.declaredMethods
            mMethods?.forEach {mMethod->
                mMethod.isAccessible = true
                val onClick: OnClick? = mMethod.getAnnotation(OnClick::class.java)
                onClick?.let {click ->
                    val viewId = click.resourceID
                    val method: Method? = findMethod(any, "findViewById", Int::class.java)
                    val view = method?.invoke(any, viewId)
                    view?.let {v->
                        (v as View).setOnClickListener {
                            mMethod.invoke(any)
                        }
                    }
                }
            }
        }

        @Throws(NoSuchMethodException::class)
        fun findMethod(instance: Any, name: String, vararg parameterTypes: Class<*>?): Method? {
            var clazz: Class<*>? = instance.javaClass
            while (clazz != null) {
                try {
                    val method = clazz.getDeclaredMethod(name, *parameterTypes)
                    if (!method.isAccessible) {
                        method.isAccessible = true
                    }
                    return method
                } catch (e: NoSuchMethodException) {
                    // ignore and search next
                }
                clazz = clazz.superclass
            }
            throw NoSuchMethodException(
                "Method "
                        + name
                        + " with parameters "
                        + Arrays.asList(parameterTypes)
                        + " not found in " + instance.javaClass
            )
        }

        fun getFiled(obj : Any, name : String) : Field?{
            var clz : Class<Any>? = obj.javaClass
            while (true){
                try {
                    if (clz == null)
                        break
                    val field: Field = clz.getDeclaredField(name)
                    field.isAccessible = true
                    return field
                }catch (e : NoSuchFieldException){

                }
                if (clz == null)
                    break
                clz = clz.superclass
                if (clz == null)
                    break
            }
            return null
        }

    }



}