package com.excalibur.enjoylearning.vm

import android.app.Application
import android.util.Log
import java.io.File
import java.io.IOException
import java.lang.reflect.Array.newInstance
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

//TODO 2021.12.08
//TODO 1.getFiled和getMethod要加入递归遍历子类
//TODO 2.makeElementMethod各版本兼容
//TODO 3.两个Array的合并要考虑到泛型擦除
class HotfixUtils {

    fun fix(context: Application, file: File){

        if(!file.exists())
            return

        //1.获取当前应用PathClassLoader
        val loader = context.classLoader

        //2.反射获取到pathList
//        val pathListField : Field = loader.javaClass.getDeclaredField("pathList")
        val pathListField : Field? = getFiled(loader, "pathList")
        if(pathListField == null){
            Log.e("TestForCase","pathListField == null")
            return
        }
        pathListField.isAccessible = true
        val pathList = pathListField.get(loader)
        if(pathList == null){
            Log.e("TestForCase","pathList == null")
            return
        }

        //3.反射修改pathList中的dexElements；
        //把补丁包new Demo.dex转化为Element[]
//        val makeElementMethod = pathList.javaClass.getDeclaredMethod("makePathElements")
        val makeElementMethod : Method? = findMethod(
            pathList, "makePathElements",
            MutableList::class.java,
            File::class.java,
            MutableList::class.java
        )
        if(makeElementMethod == null){
            Log.e("TestForCase","makeElementMethod == null")
            return
        }
        makeElementMethod.isAccessible = true

        val fileList : List<File> = listOf(file)
        val dic : File = context.filesDir
        val exceptionList : List<IOException> = listOf()
        val elementList = makeElementMethod.invoke(pathList,fileList, dic, exceptionList) as Array<*>

        //获取pathList中的dexElements
//        val dexElementsFiled : Field = pathList.javaClass.getDeclaredField("dexElements")
        val dexElementsFiled : Field? = getFiled(pathList, "dexElements")
        if(dexElementsFiled == null){
            Log.e("TestForCase","dexElementsFiled == null")
            return
        }
        dexElementsFiled.isAccessible = true
        val dexElements = dexElementsFiled.get(pathList) as Array<*>
        //合并二者，反射赋值给dexElements
        val result = elementList.copyOf(elementList.size + dexElements.size)
        System.arraycopy(dexElements, 0, result, elementList.size, dexElements.size)
        dexElementsFiled.set(pathList, result)
        Log.e("TestForCase","System.arraycopy success,length:${result.size}")
    }

    private fun getFiled(obj : Any, name : String) : Field?{
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

//    private fun <T> getMethod(obj : Any, name : String, vararg classes : Class<Any>) : Method?{
//        var clz : Class<Any>? = obj.javaClass
//        while (true){
//            try {
//                if (clz == null)
//                    break
//                val method = clz.getDeclaredMethod(name, classes)
//                method.isAccessible = true
//                return method
//            }catch (e : NoSuchMethodException){
//
//            }
//            if (clz == null)
//                break
//            clz = clz.superclass
//            if (clz == null)
//                break
//        }
//        return null
//    }

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

//    @Throws(
//        NoSuchFieldException::class,
//        IllegalArgumentException::class,
//        IllegalAccessException::class
//    )
//    fun expandFieldArray(instance: Any?, fieldName: String?, patchElements: Array<Any?>) {
//        //拿到 classloader中的dexelements 数组
//        val dexElementsField: Field = FieldFinder.findField(instance, fieldName)
//        //old Element[]
//        val dexElements = dexElementsField[instance] as Array<Any>
//
//
//        //合并后的数组
//        val newElements = Array.newInstance(
//            dexElements.javaClass.componentType,
//            dexElements.size + patchElements.size
//        ) as Array<Any>
//
//        // 先拷贝新数组
//        System.arraycopy(patchElements, 0, newElements, 0, patchElements.size)
//        System.arraycopy(dexElements, 0, newElements, patchElements.size, dexElements.size)
//
//        //修改 classLoader中 pathList的 dexelements
//        dexElementsField[instance] = newElements
//    }


}