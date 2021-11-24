package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO 编译时常量，只能是基本数据类型
const val info = "nagisa"

//TODO 关于kotlin的数据类型和语法
class Kotlin1GrammarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutVariable()
        aboutConstant()
        aboutRepeat()
        aboutString()
        aboutFunction("name","age")
        aboutFunction("name")                      //默认参数
        aboutFunction(age = "age",name = "name")        //具名函数参数
        aboutFunction1()                                //Unit函数
        `为了测试`("name","age")               //作用1：单元测试，2：is in关键字，3：独特的函数命名混淆，内部有一份函数名对应功能的文档
    }

    //TODO 可读可改的变量var和只读变量val
    private fun aboutVariable(){
        //申明
        var name : String = "nagisa"
        val pwd : String = "nagisa"
        var age = 18
    }

    //TODO 常量
    private fun aboutConstant(){
        println(info)
    }

    //TODO 循环：in range,when
    private fun aboutRepeat(){
        for (i in 10..20){
            if(i !in 10..100){

            }
        }

        val age = 10
        val info : Any = when(age){
            -1 -> TODO("msg from me")    //会终止when继续执行，抛出一个异常
            1 -> "nagisa"
            2 -> "lacus"
            else -> {
                println("lazy one")
            }          //必不可少
        }
    }

    //TODO 字符串的拼接
    private fun aboutString(){
        val name = "nagisa"
        println("字符串的拼接：${name}，还有一种方式$name 哈哈")
        val ok = false
        println("试一试:${if (ok) "是真的" else "是假的"}")
    }

    //TODO 函数相关
    private fun aboutFunction(name : String, age : String = "nagisa") : Int{
        return 100
    }
    private fun aboutFunction1() : Unit{
        //Unit可不写，默认就有，代表无返回
    }
    private fun `为了测试`(name: String,age: String){
        //为了做单元测试的函数，单引号中的是函数说明
    }
}