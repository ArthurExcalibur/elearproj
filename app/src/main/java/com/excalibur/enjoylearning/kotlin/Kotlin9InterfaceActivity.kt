package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO kotlin中的接口和抽象类
class Kotlin9InterfaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutInterface()                            //接口定义
        aboutInterfaceDefaultImpl()                 //接口默认实现
        aboutAbstractClass()                        //抽象类
        aboutGeneric()                              //泛型类
        aboutGenericFunction()                      //泛型函数
        aboutGenericTransfer()                      //泛型类型转换
        aboutGenericConstraints()                   //泛型类型约束
    }

    private fun aboutInterface(){
        val kt1 : interfaceKT1 = interfaceKT1Impl1("override version")
        println(kt1.getInfo())

        val kt2 : interfaceKT1 = interfaceKT1Impl2()
        kt2.version = "override version"
        println(kt2.getInfo())
    }

    private fun aboutInterfaceDefaultImpl(){
        val kt1 : interfaceKT2
    }

    private fun aboutAbstractClass(){
        val clz : abstractClass1 = abstractClass1Impl()
        clz.abstractFun()
    }

    private fun aboutGeneric(){
        GenericClass(true,GenericImpl1("",1)).show()
        GenericClass(true,GenericImpl2('M',"")).show()
        GenericClass(true,10).show()
        GenericClass(true,"").show()
        GenericClass(true,'A').show()
    }

    private fun aboutGenericFunction(){
        //GenericImpl1
        val obj1 = GenericClass(true,GenericImpl1("",1)).GenericFunction()
        //null
        val obj2 = GenericClass(false,GenericImpl1("",1)).GenericFunction()

        val result = GenericClass(true,10).GenericFunction()?.run {
            "value is $this"
        } ?: "value is null"

        GenericClass(true,'A').GenericFunction().apply {
            if(this == null){
                println("value is null")
            } else{
                println("value is $this")
            }
        }

        GenericClass(true,10).printGeneric(GenericImpl1("",1))
    }

    private fun aboutGenericTransfer(){
        //isMap为true，把122转换成string，isMap为false，返回null
        val transferClass = GenericTransferClass(isMap = true, inputType = 122)
        val res : String? = transferClass.transfer {
            "output value : $it"
        }
        println(res)

        //将GenericImpl1转换成GenericImpl2
        val transferClass1 = GenericTransferClass(isMap = true, inputType = GenericImpl1("name",1))
        val res1 : GenericImpl2? = transferClass1.transfer {
            GenericImpl2('M', it.name)
        }
        println(res1)

        //和上面的代码是等价的
        transferMap(inputType = 122) {
            "output value : $it"
        }
        transferMap(inputType = GenericImpl1("name",1)) {
            GenericImpl2('M', it.name)
        }
    }

    inline fun <I,O> transferMap(isMap: Boolean = true,inputType : I,transferAction : (I) -> O) : O? =
        if(isMap) transferAction(inputType) else null

    private fun aboutGenericConstraints(){
        ConstraintsClass(GenericConstraintsClass()).printT()
        ConstraintsClass(childGenericConstraintsClass()).printT()
        //下面这个不彳亍，泛型约束了必须是GenericConstraintsClass类型的
        //ConstraintsClass(notChildGenericConstraintsClass()).printT()

        transferMap1(isMap = true,inputType = GenericConstraintsClass())
        transferMap1(isMap = true,inputType = childGenericConstraintsClass())
        //下面这个不彳亍，泛型约束了必须是GenericConstraintsClass类型的
        //transferMap1(isMap = true,inputType = notChildGenericConstraintsClass())
    }

    fun <I:GenericConstraintsClass> transferMap1(isMap: Boolean = true,inputType : I) = println(inputType)
}

interface interfaceKT1{
    //1.接口内所有成员和接口本身默认是open的
    //2.接口不带构造函数
    //3.实现类不仅要重写接口的方法，也要重写参数
    //4.override
    var version: String
    fun getInfo() : String
}
class interfaceKT1Impl1(override var version : String = "default version") : interfaceKT1{
    override fun getInfo() = version
}
class interfaceKT1Impl2() : interfaceKT1{
    //必须经过初始化（="default version")之后field才会被持有
    override var version: String = "default version"
        get() = field
        set(value) {field = value}
    override fun getInfo() = version
}

interface interfaceKT2{
    //默认赋值（重写get方法，必须是val类型）（不建议这么做，接口定义的还是交给具体实现类操作）
    val version: String
        get() = "default value"
}

abstract class abstractClass1{
    abstract fun abstractFun()
}
class abstractClass1Impl : abstractClass1(){
    override fun abstractFun() {
        println("abstractFun impl")
    }
}

//泛型类
class GenericClass<T>(private val printObj : Boolean, val obj : T){
    fun show() = println("输出对象:$obj")
    //返回泛型
    fun GenericFunction() : T? = obj.takeIf { printObj }
    //参数泛型
    fun <T> printGeneric(item : T) = item ?. apply {
        println(this)
    } ?: println("null value")
}
data class GenericImpl1(val name : String, val age : Int)
data class GenericImpl2(val sex : Char, val msg : String)

class GenericTransferClass<T>(val isMap : Boolean, val inputType : T){
    //模仿了RxJava
    //T是要变化的输入类型，R是变化后的输出类型
    //transfer返回的类型是R？，因为搭配了takeIf
    inline fun <R> transfer(transferAction : (T) -> R) : R? = transferAction(inputType).takeIf { isMap }
}

open class GenericConstraintsClass()
class childGenericConstraintsClass : GenericConstraintsClass()
class notChildGenericConstraintsClass()
class ConstraintsClass<T : GenericConstraintsClass>(val inputType : T){
    fun printT() = println(inputType)
}