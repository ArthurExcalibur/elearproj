package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File

//TODO kotlin中的对象
class Kotlin8ObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutExtends()                  //类的继承和方法重写
        aboutTypeTransfer()             //类型判断和转换 is as，智能类型转换
        aboutAny()                      //Any超类
        aboutObjectAffirm()             //对象申明
        aboutObjectExpression()         //对象表达式
        aboutAssociatedObject()         //伴生对象
        aboutInnerClass()               //嵌套类和内部类
        aboutDataClass()                //数据类
        aboutOperatorOverloading()      //运算符重载
        aboutEnum()                     //枚举类
        aboutEnumAlgebra()              //代数数据类型
        aboutSealedClass()              //密封类
    }

    private fun aboutExtends(){
        val person : ObjectClassKt1 = ObjectClassKt1Child("name")
        person.printName()
    }

    private fun aboutTypeTransfer(){
        val person : ObjectClassKt1 = ObjectClassKt1Child("name")

        person is ObjectClassKt1
        person is ObjectClassKt1Child
        person is File

        (person as ObjectClassKt1).printName()
        (person as ObjectClassKt1Child).printName()

        person.methodFather()
        //智能类型转换：有了上面的person as ObjectClassKt1Child转换之后，person就变成了ObjectClassKt1Child类型
        //如果没有上面的转化，直接调用methodChild会报错（因为还是ObjectClassKt1类型）
        person.methodChild()
    }

    private fun aboutAny(){
        //Any相当于java中的Object，所有对象都隐式继承它
        //KT设计中Any只提供标准，没有实现，具体实现依赖各个平台（Object是有实现的）
        val name : Any = ""
    }

    private fun aboutObjectAffirm(){
        //object ObjectClassKt2既是单例的实例，也是类名
        //如下，两个打印的地址是一样的，说明是单例，而.show也可以直接通过对象点出来
        println(ObjectClassKt2)
        println(ObjectClassKt2)
        ObjectClassKt2.show()
    }

    private fun aboutObjectExpression(){
        //匿名对象:表达式方式
        val child : ObjectClassKt3 = object : ObjectClassKt3() {
            override fun add(info: String) {
                super.add(info)
                println("Anonymous add area")
            }

            override fun del(info: String) {
                super.del(info)
                println("Anonymous del area")
            }
        }
        child.add("add")
        child.del("del")
        //具名实现：写一个类ObjectClassKt4继承ObjectClassKt3
        //val child : ObjectClassKt3 = ObjectClassKt4()
        //child.add("add")
        //child.del("del")
        //所以就是java的匿名内部类和外部类？？？？？？？

        //关于接口：java的接口KT有两种实现方式（第二种就是第一种的lambda）；KT的接口只有一种实现方式（object:对象表达式）
        val p = object : Runnable{
            override fun run() {
                println("KT 实现java接口 方式1")
            }
        }
        p.run()
        val p1 = Runnable {
            println("KT 实现java接口 方式2")
        }
        p1.run()

        val p3 = object : RunnableKT{
            override fun run() {
                println("KT 实现KT接口 方式1")
            }
        }
        p3.run()
//        val p4 = RunnableKT{
//
//        }

    }

    private fun aboutAssociatedObject(){
        ObjectClassKt4.data
        ObjectClassKt4.show()
    }

    private fun aboutInnerClass(){
        //嵌套类：内部不能访问外部，外部可以访问内部
        ObjectClassKt5.ObjectClassKt5Child1().child1Run()
        ObjectClassKt5("").runChild1()
        //内部类：内部外部可以互相访问
        ObjectClassKt5("").ObjectClassKt5Child2().child2Run()
        ObjectClassKt5("").runChild2()
        ObjectClassKt5("").ObjectClassKt5Child3().ObjectClassKt5Child4().child4Run()
        ObjectClassKt5("").runChild4()
    }

    private fun aboutDataClass(){
        val obj = ObjectClassKt6("")
        println(obj.coreInfo)
        //数据类中多生成的那些方法只考虑主构造，所以调用copy生成新的obj之后coreInfo会是空（因为没有走次构造）
        val newObj = obj.copy("",200,"")
        println(newObj.coreInfo)
        //普通类和数据类解构
        val (msg,code,data) = ObjectClassKt6Usual("",200,"")
        val (_,code1,data1) = ObjectClassKt6("",200,"")

        //使用场景（条件）
        //1.服务器请求返回的数据（JavaBean）
        //2.数据类至少必须有一个参数的主构造
        //3.数据类必须有参数（var val 的参数）
        //4.数据类不能使用abstract open sealed inner等修饰（只做数据处理，不做其余工作）
        //5.需求比较，copy，tostring，解构等这些功能时，也可使用数据类
    }

    private fun aboutOperatorOverloading(){
        val obj : ObjectClassKt7 = ObjectClassKt7(1) + ObjectClassKt7(2)
        println(obj.number)
    }

    private fun aboutEnum(){
        println(ObjectClassKt8.Fir)
        println(ObjectClassKt8.Mon)
        //枚举的值等于枚举本身
        println(ObjectClassKt8.Fir is ObjectClassKt8)//true

        ObjectClassKt8.Mon.show()

        ObjectClassKt8.Mon.update(ObjectClassKt8Info("",1))
    }

    private fun aboutEnumAlgebra(){
        println(WeekDay(ObjectClassKt9.Mon).show())
    }

    private fun aboutSealedClass(){
        println(NewWeekDay(ObjectClassKt10.Mon).show())
        println(NewWeekDay(ObjectClassKt10.Thu("name")).show())

        println(ObjectClassKt10.Mon === ObjectClassKt10.Mon)//true（object修饰的，单例）
        println(ObjectClassKt10.Thu("name") === ObjectClassKt10.Thu("name"))//false（class修饰的，非单例）
    }

}

//KT中的类默认是public final的，不可继承
//需要加上open移除final修饰
open class ObjectClassKt1(val name : String){
    private fun showName() = "ObjectClassKt1 print name : $name"
    //KT中的函数默认是public final的，不可重写
    //需要加上open移除final修饰
    open fun printName() = println("ObjectClassKt1 printName:${showName()}")
    fun methodFather() = println("methodFather")
}
class ObjectClassKt1Child(val subName : String) : ObjectClassKt1(subName){
    private fun showName() = "ObjectClassKt1Child print name : $subName"
    override fun printName() {
        super.printName()
        println("ObjectClassKt1Child printName:${showName()}")
    }
    fun methodChild() = println("methodChild")
}

object ObjectClassKt2{
    //ObjectClassKt2后面不能写（），就好比单例不能把主构造暴露给外部，类似如下java内部代码
    //private ObjectClassKt2(){}
    //public static final ObjectClassKt2 INSTANCE
    //init{} =====>  static{ObjectClassKt2 k = new ObjectClassKt2(),INSTANCE=k}
    //ObjectClassKt2.show()     ====>   INSTANCE.show()
    init { }
    fun show() = println()
}

open class ObjectClassKt3{
    open fun add(info : String) = println("add msg : $info")
    open fun del(info : String) = println("del msg : $info")
}

interface RunnableKT{
    fun run()
}

class ObjectClassKt4{
    //伴生对象(KT中没有static，伴生很大程度就类似静态）
    //ObjectClassKt4可实例化多个，伴生对象只有一个
    //背后生成Companion类
    //public static final ObjectClassKt4.Companion c = new XXX
    //private static final String data = "Nagisa"
    //ObjectClassKt4.data    ====>   ObjectClassKt4.Companion.getData()
    companion object{
        val data = "Nagisa"
        fun show() = println(data)
    }
}

class ObjectClassKt5(val name: String){
    //不加inner的是嵌套类，不能访问外部类属性
    class ObjectClassKt5Child1{
        //fun run() = println(name)
        fun child1Run() = println()
    }
    //加上inner才是内部类，能访问外部类属性
    inner class ObjectClassKt5Child2{
        fun child2Run() = println(name)
    }
    inner class ObjectClassKt5Child3{
        inner class ObjectClassKt5Child4{
            fun child4Run() = println(name)
        }
    }

    fun runChild1() = ObjectClassKt5Child1().child1Run()
    fun runChild2() = ObjectClassKt5Child2().child2Run()
    fun runChild4() = ObjectClassKt5Child3().ObjectClassKt5Child4().child4Run()
}

class ObjectClassKt6Usual(var msg: String,var code : Int,var data : String){
    //普通类加解构：必须加上operator关键字，component1方法顺序不能变，从1开始，对应主构造的每一个字段
    operator fun component1() = msg
    operator fun component2() = code
    operator fun component3() = data
}
data class ObjectClassKt6(var msg: String,var code : Int,var data : String){
    //加上data就是数据类，一般用于Javabean，比普通类多了很多内部方法
    //普通类：set，get
    //数据类：set，get，解构，copy，toString，hashcode，equals
    var coreInfo : String = ""
    constructor(msg: String) : this(msg,200,""){
        coreInfo = "coreInfo"
    }
}

class ObjectClassKt7(var number: Int){
    operator fun plus(k : ObjectClassKt7) : ObjectClassKt7{
        return ObjectClassKt7(k.number + number)
    }
    //查看所有可重载的运算符
    //operator fun ObjectClassKt7.
}

//枚举类定义函数（加了这个之后枚举内部定义值就必须带上它）
class ObjectClassKt8Info(val info: String, val length : Int){
    fun show() = println("msg : $info,length : $length")
}
//枚举的主构造参数必须和枚举的参数保持一致
enum class ObjectClassKt8(private var info: ObjectClassKt8Info){
    Mon(ObjectClassKt8Info("",1)),
    Fir(ObjectClassKt8Info("",5));

    fun show() = println("msg : ${info.info},length : ${info.length}")
    fun update(newInfo: ObjectClassKt8Info){
        info = newInfo
    }
}

enum class ObjectClassKt9(){
    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat,
    Sun;
}
class WeekDay(private val enum : ObjectClassKt9){
    fun show() = when(enum){
        ObjectClassKt9.Mon -> "星期一"
        ObjectClassKt9.Tue -> "星期二"
        ObjectClassKt9.Wed -> "星期三"
        ObjectClassKt9.Thu -> "星期四"
        ObjectClassKt9.Fri -> "星期五"
        ObjectClassKt9.Sat -> "星期六"
        ObjectClassKt9.Sun -> "星期日"
        //else -> ""
        //使用枚举类型做when判断，属于代数数据类型，不需要写else（把枚举的所有分支都写了）
    }
}

//密封类，以sealed关键字开头
sealed class ObjectClassKt10{
    //成员必须有类型（object，class），且必须继承本类ObjectClassKt10
    object Mon : ObjectClassKt10()
    object Tue : ObjectClassKt10()
    object Wed : ObjectClassKt10()
    class Thu(val weekName : String) : ObjectClassKt10()
}
class NewWeekDay(private val enum : ObjectClassKt10){
    fun show() = when(enum){
        is ObjectClassKt10.Mon -> "星期一"
        is ObjectClassKt10.Tue -> "星期二"
        is ObjectClassKt10.Wed -> "星期三"
        is ObjectClassKt10.Thu -> "星期四，附带名字是:${this.enum.weekName}"
    }
}