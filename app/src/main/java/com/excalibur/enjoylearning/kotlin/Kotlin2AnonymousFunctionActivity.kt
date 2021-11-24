package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO 关于kotlin的函数
class Kotlin2AnonymousFunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        方法名 : 必须指定参数类型和返回类型
//        val method : (String) -> String = {
//            it
//        }
//        val methodAction : (Int,String,Int) -> String = {count,params,position ->
//            ""
//        }
//        方法名 = 类型推断返回类型
//        val method = {it:String ->
//            it
//        }
//        val methodAction = {count:String,params:String,position:String ->
//            ""
//        }

        aboutAnonymousFunction()//匿名函数
        aboutLanguageFunction()//语言函数
        aboutParamsFunction()//函数参数说明
        aboutAnonymousFunctionType()//匿名函数类型推断
        aboutFunctionAsParams()//定义参数是函数的函数
        aboutInlineFunction()//内联函数
        aboutFunctionQuote()//函数引用
        aboutReturnFunction()//函数类型作为返回类型
        aboutAnonymousAndNamedFunction()//匿名函数和具名函数
    }

    private fun aboutAnonymousFunction(){
        val len = "hello".count()
        println(len)

        val len2 = "hello".count { it == 'l' }
        println(len2)
    }

    private fun aboutLanguageFunction(){
        //函数申明
        val methodAction : (Int,String,Int) -> String
        //函数实现
        methodAction = {count,params,position ->
            "size"//不带return，最后一行就是返回
        }
        //函数调用
        val result = methodAction
    }

    private fun aboutParamsFunction(){
        //将函数申明和实现一起完成
        val methodAction : (Int,String,Int) -> String = {count,params,position ->
            ""
        }
        val result = methodAction

        //it关键字，单个参数时隐式参数
        val methodAction2 : (String) -> String = {
            ""//这里it就等于传进的那个String
        }
        val methodAction3 : (Double) -> String = {
            ""//这里it就等于传进的那个Double
        }
    }

    private fun aboutAnonymousFunctionType(){
        val methodAction = {p1 : String , p2 : String , p3 : String ->
            "nagisa"
        }
    }

    private fun aboutFunctionAsParams(){
        //这里的responseResult : (String, Int) -> Unit就相当于java中设置的Callback或者Listener（接口）
        //调用本函数时，传入用户名密码，经过校验后的结果以response的方法返回（相当于请求接口，String是Data，Int是Code）
//        val method = {name : String, pwd : String, responseResult : (String, Int) -> Unit ->
//            if(name == null || pwd == null)
//                TODO("Data is null")
//            if(name == "dx" && pwd == "123")//这里的结果可以是经过很多次判断的
//                responseResult("login success",200)
//            else
//                responseResult("login error",404)
//        }

//        当最后一个参数是函数的时候，可以从小括号里面移到外面，叫做函数尾随
//        login("dd","111",{ data: String, code: Int ->
//            println("login result:$data,$code")
//        })
        login("dd","111") { data: String, code: Int ->
            println("login result:$data,$code")
        }
    }

    private fun aboutInlineFunction(){
        //使用lambda作为参数的函数，需要申明为内联函数
        //如果不使用内联，调用时会生成多个对象来调用（匿名内部类？？？），对性能有影响
        // inline fun login(name : String, pwd : String, responseResult : (String, Int) -> Unit)
    }

    private fun aboutFunctionQuote(){
        //函数引用
        //lambda属于函数类型的对象，需要把普通函数（methodResponseResult）变成函数类型的对象（函数引用），才能放到第三个参数
//        val obj = ::methodResponseResult
//        login("dx","123",obj)
        login("dx","123",::methodResponseResult)
    }

    private fun aboutReturnFunction(){
        val method = resultFunction("info")
        val result = method("dx","123")
    }

    private fun aboutAnonymousAndNamedFunction(){
        //匿名函数
        login("dd","111") { data: String, code: Int ->
            println("login result:$data,$code")
        }
        //具名函数
        login("dd","111",::methodResponseResult)
    }

    inline fun login(name : String, pwd : String, responseResult : (String, Int) -> Unit){
        if("dx" == name && "123" == pwd)
            if(name == "dx" && pwd == "123")//这里的结果可以是经过很多次判断的
                responseResult("login success",200)
            else
                responseResult("login error",404)
    }

    fun methodResponseResult(msg:String,code:Int){
        println("login result:$msg,$code")
    }

    fun resultFunction(info:String) : (String,String) -> String {
        //返回一个匿名函数
        return {name : String, pwd : String -> String
            info
        }
    }

}