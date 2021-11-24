package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO kotlin中的类
//TODO 70
class Kotlin7ClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutClassAndFiled()                        //定义类和field关键字
        aboutPreventRaceConditions()                //计算属性和防范竞态条件
        aboutConstructor()                          //主构造方法
        aboutConstructorParams()                    //主构造函数中定义属性
        aboutSubConstructor()                       //次构造方法
        aboutSubConstructorParams()                 //次构造方法默认值
        aboutInitCodeArea()                         //init代码块
        aboutConstructorOrder()                     //构造方法初始化顺序
        aboutLateinit()                             //延时初始化
    }

    private fun aboutClassAndFiled(){
        ClassKt().name = ""
    }

    private fun aboutPreventRaceConditions(){
        println(ClassKt1().number)
        println(ClassKt1().number1)

        println(ClassKt1().getShowInfo())
    }

    private fun aboutConstructor(){
        println(ClassKt2("","",1,2).name)
    }

    private fun aboutConstructorParams(){
        println(ClassKt3("","",1,2).name)
    }

    private fun aboutSubConstructor(){
        //println(ClassKt4("","",1,2).name)
        println(ClassKt4("","").name)
        println(ClassKt4("","",1,2,3).name)
    }

    private fun aboutSubConstructorParams(){
        println(ClassKt5("","").name)
        println(ClassKt5("").name)
        println(ClassKt5().name)
    }

    private fun aboutInitCodeArea(){
        ClassKt6("","").name = ""
    }

    private fun aboutConstructorOrder(){
        ClassKt7("","").name = ""
    }

    private fun aboutLateinit(){
        ClassKt8().showName()
    }

}

class ClassKt(){
    var name = "dx"
        get() = field
        set(value) {
            field = value
        }
    //默认就有field那些代码，写不写都一样
    var pass = "123"
}

class ClassKt1(){
    val number : Int = 1
    //计算属性
    //get被覆盖了，field就失效了
    val number1 : Int
        get() = (1..1000).shuffled().first()
    //防范竞态条件
    //当调用某个可能为空的属性时，需要采用防范竞态条件（KT规范化）
    var info : String? = null
    fun getShowInfo() : String?{
        return info?.also {
            println(it)
        } ?: "null,plz check code"
    }
}

//主构造函数：_XXX，输入的临时类型不能直接用，需要定一个内部变量接收下来才能使用
class ClassKt2(_name : String, _pass : String, _number : Int, _numner1 : Int){

    var name = _name
        get() = field
        set(value) {
            if(value == null)
                field = "dx"
            else
                field = value
        }

    fun show(){
//        println(_name)
        println(name)
    }

}

//主构造函数中定义属性
class ClassKt3(var name : String, var pass : String = "222", var number : Int, var numner1 : Int){

    fun show(){
        println(name)
    }

}

//次构造，必须调用主构造（为了让主构造统一管理）
class ClassKt4(var name : String, var pass : String, var number : Int, var numner1 : Int){

    constructor(name: String,pass: String): this(name,pass,1,2){

    }

    constructor(name: String,pass: String,number:Int,numner1: Int,number2:Int): this(name,pass,number,numner1){

    }

}

//次构造方法默认值
class ClassKt5(var name : String, var pass : String, var number : Int, var numner1 : Int){

    constructor(name: String = "XXXX",pass: String = "YYYY"): this(name,pass,1,2){

    }

}

//初始化块
class ClassKt6(_name : String, _pass : String){

    init {
        //主构造调用前会调用这里的代码
        //如果是次构造，先init--->主构造---->次构造
        //这里可以用临时类型
        require(_name.isNotBlank()){"null name value"}//false抛出异常，后面的是错误日志
        println(_name)
    }

    var name = _name
    fun show(){
//        println(_name)
        println(name)
    }
}

//构造方法初始化顺序
//Log.e("TestForCase",ClassKt7("dx","123",11).pass)
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: init
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: n被赋值了dx
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: constructor(name: String,pass: String,age:Int)
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: get1111111()
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: agg被赋值了11
//2021-11-24 20:37:18.972 5324-5324/com.excalibur.enjoylearning E/TestForCase: 123
class ClassKt7(_name : String, val pass : String){

    var name = _name
        get() {
            println("get()")
            return field
        }
        set(value) {
            println("set()")
            field = value
            println("name被赋值了$name")
        }

    init {
        println("init")
        val n = _name
        println("n被赋值了$n")
    }

    var agg : Int = 1
        get() {
            println("get1111111()")
            return field
        }
        set(value) {
            field = value
            println("agg被赋值了$agg")
        }
    constructor(name: String,pass: String,age:Int) : this(name,pass){
        println("constructor(name: String,pass: String,age:Int)")
        agg = age
    }

    var msg = "AAA"

    //1.val pass : String
    //2. var name = _name
    //3.init
    //4.var msg = "AAA"
    //5.constructor(name: String,pass: String,age:Int)

}

//延迟初始化
class ClassKt8(){
    lateinit var name: String

    fun showName(){
        //如果未初始化，即使判空都会抛出异常
        //if(name == null)
        //用::name.isInitialized判断是否初始化
        if(::name.isInitialized){
            println(name)
        }
    }

}

//惰性加载
class ClassKt9(){
    lateinit var name: String

    fun showName(){
        //如果未初始化，即使判空都会抛出异常
        //if(name == null)
        //用::name.isInitialized判断是否初始化
        if(::name.isInitialized){
            println(name)
        }
    }

}