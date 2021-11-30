package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.excalibur.enjoylearning.kotlin.extension.randomGetValue
import com.excalibur.enjoylearning.kotlin.extension.randomGetValuePrint as rP

//TODO kotlin中的关键字
//TODO 107
class Kotlin10KeywordActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutVararg()                           //vararg关键字（动态参数）
        aboutBracketsOperator()                 //[]操作符
        aboutOut()                              //out关键字
        aboutIn()                               //in关键字
        aboutReified()                          //reified关键字
        aboutExtensionFunction()                //扩展函数
        aboutAnyExtensionFunction()             //对超类的扩展函数
        aboutGenericExtensionFunction()         //泛型扩展函数
        aboutAttributesExtensionFunction()      //属性扩展
        aboutNullableExtensionFunction()        //可空类型扩展函数
        aboutInfix()                            //infix关键字
        aboutExtensionFile()                    //扩展文件
        aboutReNameExtension()                  //重命名扩展
    }

    private fun  aboutVararg(){
        val p : VarargClass<Any> = VarargClass(true,"1",2,'M',123.2f,122.2)
        p.showObjects()
//        val p1 : VarargClass<Any?> = VarargClass(true,"1",2,'M',123.2f,122.2,null)

        VarargClass(transfer = true,"1",2,'M',123.2f,122.2).showObjectByIndex(2)
    }

    private fun aboutBracketsOperator(){
        val p = BracketClass(true,"1","2",3,'4')

        println(p[0])//运算符重载，会调用get函数
        println(p[1])
    }

    private fun  aboutOut(){
        //默认情况下，泛型实现处的子类对象不能赋值给泛型声明处父类对象（Producer不加out，p2,p3,p4都会报错）
        //加上out后就可以
        val p1 : Producer<Animal> = ProducerImpl1()
        val p2 : Producer<Animal> = ProducerImpl2()
        val p3 : Producer<Animal> = ProducerImpl3()
        val p4 : Producer<Animal> = ProducerImpl4()
        //就相当于java里面的下面代码，第一行会报错，但是加上? extends就不会
        //out T就相当于? extends T，让泛型实现处的子类对象可以赋值给泛型声明处的父类对象
        //List<CharSequence> list = new ArrayList<String>()
        //List<? extends CharSequence> list = new ArrayList<String>()
    }

    private fun  aboutIn(){
        //默认情况下，泛型实现处的父类是不能赋值给声明处的（Consumer不加in，p1,p2都会报错）
        //加上in后就可以
        val p1 : Consumer<Women> = ConsumerImpl1()
        val p2 : Consumer<Man> = ConsumerImpl1()
        //就相当于java里面的下面代码，第一行会报错，但是加上? super就不会
        //in T就相当于? super T，让泛型实现处的父类对象可以赋值给泛型声明处的子类对象
        //List<String> list = new ArrayList<CharSequence>()
        //List<? super String> list = new ArrayList<CharSequence>()
    }

    private fun  aboutReified(){
        val finalObj = RandomClass().randomReifiedClass<ReifiedClass1> {
            println("not compare")
            ReifiedClass1("defaultName")
        }
        println("finalObj:$finalObj")
    }

    private fun  aboutExtensionFunction(){
        val p = ExtensionFunctionClass("default name")
        p.show()
        p.name
    }

    private fun  aboutAnyExtensionFunction(){
        12.2.doSth()
        true.doSth()
        "123".doSth().doSth().doSth()
    }

    private fun aboutGenericExtensionFunction(){
        //函数，基本数据类型，对象，都可以当做泛型，调用泛型扩展函数
        1111.showInfo()
        12.2.showInfo()
        true.showInfo()
        "123".showCallType()
        Animal().showCallTime()

        //模仿系统let函数：
        //inline fun <I, O> I.mLet(lambda : (I) -> O) = lambda(this)
    }

    private fun aboutAttributesExtensionFunction(){
        //String.myInfo:相当于给每一个String添加一个myInfo的属性
        val string = "Arthur"
        println(string.myInfo)

        //扩展属性搭配扩展函数
        string.myInfo.showInfo().showInfo().showCallTime().showCallType()
    }

    private fun  aboutNullableExtensionFunction(){
        val value : String? = null
        //Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
        //value.myInfo
        println(value.myInfoNullable)

        "val".printInfo("default")  //val
        value.printInfo("default")  //default
    }

    private fun  aboutInfix(){
        //infix:中缀表达式，可以简化代码
        //public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
        mapOf("key".to("value"))
        mapOf("key" to "value")

        val p = "key".some("value")
        val p1 = "key" some "value"

        123.some("val")
        122.6f.some(13)
        true some 14
        //...
    }

    private fun aboutExtensionFile(){
        val list = listOf("1","2","3")
        list.shuffled().first()
        list.randomGetValue()
    }

    private fun aboutReNameExtension(){
        val list = listOf("1","2","3")
        list.rP()
        //import com.excalibur.enjoylearning.kotlin.extension.randomGetValuePrint as rP
        //list.randomGetValuePrint()
    }

}

//class VarargClass<T> (objects : List<T>)
class VarargClass<T> (transfer : Boolean,vararg objects : T){
    //out表示T只能被读取，不能被修改
    private val objectArray : Array<out T> = objects

//    fun showObjectByIndex(index : Int) = println(objectArray[index])
    fun showObjectByIndex(index : Int) = println(objectArray.elementAtOrNull(index).toString())
    fun showObjects() = objectArray.forEach {
        println(it.toString())
    }
}

class BracketClass<INPUT> (isTransfer : Boolean,vararg objects : INPUT) {
    private val transfer = isTransfer
    private val objectArray: Array<out INPUT> = objects

    //5种返回类型变化的解释
    //返回类型可能是Array<out INPUT>，或者null
    fun getResult1(): Array<out INPUT>? = objectArray.takeIf { transfer }
    //返回类型可能是Array<out INPUT>，也可能是String
    fun getResult2(): Any = objectArray.takeIf { transfer } ?: ""
    //返回类型可能是Array<out INPUT>，也可能是String，也可能是null（实际上不可能）
    fun getResult3(): Any? = objectArray.takeIf { transfer } ?: "" ?: null
    //返回类型可能是INPUT，或者null
    fun getResult4(index : Int) : INPUT? = objectArray.elementAtOrNull(index)
    //返回类型可能是INPUT，也可能是String，或者null（实际上不可能）
    fun getResult5(index : Int) : Any? = objectArray.elementAtOrNull(index) ?: "" ?: null

    //给泛型传入null后直接操作
    fun <INPUT> inputObj(item : INPUT){
        //传入null会崩溃（null不能被强转为String）
        println((item as String).length)
        //传入null不会崩溃（灵活运用？和？.和？：避免null导致的崩溃）
        println((item as String?)?.length ?: "null value")
    }

    //运算符重载
    operator fun get(index : Int) : INPUT? = objectArray.elementAtOrNull(index)
}

//生产者 out T 协变
interface Producer<out T>{
    //out代表T只能被读取，不能被修改
    //无法作为参数被修改
    //报错：Type parameter T is declared as 'out' but occurs in 'in' position in type T
    //fun coustom(item : T){}

    //可以作为返回类型被读取
    fun produce() : T
}
//消费者 in T 逆变
interface Consumer<in T>{
    //in代表T只能被修改，不能被读取
    //可以作为参数被修改
    fun coustom(item : T){}

    //无法作为返回类型被读取
    //报错：Type parameter T is declared as 'in' but occurs in 'out' position in type T
    //fun produce() : T
}
//默认情况下 T 不变
interface ProducerAndConsumer<T>{
    //T可以被读取，也可以被修改
    //可以作为参数被修改
    fun coustom(item : T){}

    //可以作为返回类型被读取
    fun produce() : T
}
open class Animal
open class Human : Animal()
class Man : Human()
class Women : Human()
class ProducerImpl1 : Producer<Animal>{
    override fun produce(): Animal {
        println("produce Animal")
        return Animal()
    }
}
class ProducerImpl2 : Producer<Human>{
    override fun produce(): Human {
        println("produce Human")
        return Human()
    }
}
class ProducerImpl3 : Producer<Man>{
    override fun produce(): Man {
        println("produce Man")
        return Man()
    }
}
class ProducerImpl4 : Producer<Women>{
    override fun produce(): Women {
        println("produce Women")
        return Women()
    }
}
class ConsumerImpl1 : Consumer<Animal>{
    override fun coustom(item: Animal) {
        println("coustom Animal")
    }
}

data class ReifiedClass1(val name : String)
data class ReifiedClass2(val name : String)
data class ReifiedClass3(val name : String)
class RandomClass{
    //随机产生一个ReifiedClass，如果和用户指定的是同一类型就返回对象，否则返回默认的指定对象
    //reified是用来修饰内联函数中的泛型的，使用后就可以用as T这种语法判断是否为指定泛型
    inline fun<reified T> randomReifiedClass(defaultReifiedClass : () -> T) : T?{
        val list = listOf(ReifiedClass1("ReifiedClass1")
            ,ReifiedClass2("ReifiedClass2")
            ,ReifiedClass3("ReifiedClass3"))

        val randomObj : Any = list.shuffled().first()
        println("randomObj:$randomObj")

//        return randomObj.takeIf { randomObj is T } as T? ?: defaultReifiedClass()
        return if(randomObj is T) randomObj else defaultReifiedClass()
    }
}

class ExtensionFunctionClass(val name: String)
fun ExtensionFunctionClass.show(){
    //内部持有this，等于ExtensionFunctionClass本身
    //this.name
    println("ExtensionFunction show")
}

//1.扩展函数不能重复定义（可以覆盖，比如扩展String的compareTo，原本的compareTo会被覆盖）
//2.对超类进行扩展的影响（所有对象都会拥有这个函数，慎用）
//3.扩展函数链式调用（扩展函数最后返回对象本身return this）
fun Any.doSth() : Any{
    println("Any doSth : $this")
    return this
}

fun <T> T.showInfo() = println(this)
fun <T> T.showCallTime() = println(System.currentTimeMillis())
fun <T> T.showCallType() =
    when(this){
        is String -> ""
        is Int -> ""
        else -> ""
    }

//扩展属性
//只能被String调用
val String.myInfo : String
    get() = "Nagisa"

//可以被String调用，也可以被String?调用
val String?.myInfoNullable : String
    get() = "Nagisa"
fun String?.printInfo(default : String) = println(this ?: default)

//1.自定义中缀表达式（some）要加上扩展函数（I.）一起使用
//2.需要传递一个参数（(other : O)）以供使用
public infix fun <I,O> I.some(other : O) : String = "$this,$other"