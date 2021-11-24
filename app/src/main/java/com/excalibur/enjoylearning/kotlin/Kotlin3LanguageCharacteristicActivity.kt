package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

//TODO 关于kotlin的语言特性
class Kotlin3LanguageCharacteristicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutNullCharacteristic()//可空性特点    var name : String?
        aboutSafeOperator()//安全调用操作符        name?.
        aboutLetFunction()//使用带let的安全调用     name?.let{}
        aboutNullAssert()//非空断言操作符          name!!.
        aboutIfAfterNull()//if对可空对象的判断      if(name != null){}
        aboutNullMerge()//空合并操作符            name?:
        aboutException()//异常处理和自定义异常
        aboutPrerequisites()//先决条件函数        checkNotNull,requireNotNull,require
    }

    private fun aboutNullCharacteristic(){
        //1.默认情况：不可空类型
        var name : String
        name = "nagisa"
        //name = null//置为空会报错
        //2.申明时指定为可空类型
        var name2 : String ?
        name2 = "nagisa"
        name2 = null
    }

    private fun aboutSafeOperator(){
        var name : String? = "nagisa"
        //name是可空类型，直接用会报错
        //name.length
        //name是可空类型，加上？后，如果name为空则不执行？后面的内容
        var len = name?.length
    }

    private fun aboutLetFunction(){
        var name : String? = ""
        val result = name?.let {
            //这里的it就等于name本身
            //如果能执行到这里，说明name一定不为空，在此空间执行操作都不会有问题
            if(it.isBlank())
                "default"
            else
                "null"
        }
    }

    private fun aboutNullAssert(){
        var name : String? = ""
        //加上 ！！ 表示无论name是否为空都执行后面的操作（可能会导致空指针异常）
        //百分百保证name不为空可以使用断言
        name!!.length
    }

    private fun aboutIfAfterNull(){
        var name : String? = ""
        //手动判断，勉强算一种补救措施吧。。。。。
        if(name != null)
            name.length
    }

    private fun aboutNullMerge(){
        var name : String?
        name = null
        println(name)//name为null时输出null
        println(name ?: "haha")//name为空输出"haha"，不为空输出name
        println(name ?. let { it } ?: "haha")//name为空输出"haha"，不为空输出name
    }

    private fun aboutException(){
        //意义在哪里呢？？？？？？？？？？？？？？？？？
        val checkException = {msg : String? ->
            msg ?: throw MyException()
        }
        try {
            val info : String? = ""
            checkException(info)
            println(info!!.length)
        } catch (e : Exception){

        }
    }

    class MyException : IllegalArgumentException(){

    }

    private fun aboutPrerequisites(){
        val info : String? = ""
        checkNotNull(info)//为空时会抛出一个异常IllegalStateException
        requireNotNull(info)//IllegalArgumentException
        val msg : Boolean = false
        require(msg)//IllegalArgumentException
    }

}