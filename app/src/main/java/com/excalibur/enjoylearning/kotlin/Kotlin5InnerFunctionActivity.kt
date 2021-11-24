package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

//TODO 部分kotlin内部函数与操作
class Kotlin5InnerFunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutSafeTransfer()                 //安全转化
        aboutDouble2Int()                   //double转int
        aboutApplyFunction()                //内置Apply函数（返回对象本身）(内部持有this，即对象本身）
        aboutLetFunction()                  //内置let函数（返回匿名函数的最后一行）（内部持有it，即对象本身）
        aboutRunFunction()                  //内置run函数（返回匿名函数的最后一行）（内部持有this，即对象本身）
    }

    private fun aboutSafeTransfer() {
        val number: Int = "666".toInt()
        println(number)
        //NumberFormatException
        val number2: Int = "sss".toInt()
        println(number2)
        val number3: Int? = "sss".toIntOrNull()
        println(number3)            //输出null或者转换后的数字
        println(number3 ?: "haha")      //输出haha或者转换后的数字
    }

    private fun aboutDouble2Int() {
        println(5555.5.toInt())                         //5555，舍弃小数
        println(5555.5.roundToInt())                   //5556,四舍五入
        println("%3.f".format(5555.5))          //保留三个小数点
    }

    private fun aboutApplyFunction() {
        val info = "Nagisa"
        println(info.length)
        println(info[info.length - 1])
        //println(info.toLowerCase())
        println(info.lowercase())
        info.apply {
            //apply持有的不是it，是this
            println(length)//this.省略了
            println(this[this.length - 1])
            println(lowercase())
        }
        info.apply {
            println(length)
        }.apply {
            this[this.length - 1]
        }.apply {
            lowercase()
        }
    }

    private fun aboutLetFunction() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        //1.
        val first = list.first()
        val result = first + first
        //2.
        val result1 = list.let {
            println()
            println()
            it.first() + it.first()//返回匿名函数的最后一行
        }

        val value : String? = ""
        //1.
        val method = {msg : String? ->
            if(null == msg)
                "null"
            else
                "not null"
        }
        val result2 = method(value)
        //2.
        val result3 = value?.let {
            "not null"
        } ?: "null"

    }

    private fun aboutRunFunction(){
        val info = "Nagisa"
        //匿名函数搭配run
        val method = info.run {
            length > 3
        }.run {
            if(this) "合格" else "不合格"
        }
        //具名函数搭配run
        val method1 = info.run(::runNamedFunction).run(::runNamedFunction1)
    }
    private fun runNamedFunction(str : String) = str.length > 3
    private fun runNamedFunction1(b : Boolean) = if(b) "合格" else "不合格"
}