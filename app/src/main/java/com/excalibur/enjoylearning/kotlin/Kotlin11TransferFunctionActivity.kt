package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.kotlin.java.JavaInteractiveClass

//TODO kotlin中的函数转换
//TODO 123
class Kotlin11TransferFunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutAlsoFunction()                             //also方法源码
        aboutDSL()                                      //kotlin中的DSL
        aboutTransferFunction()                         //变换函数-map
        aboutTransfer1Function()                        //变换函数-flatmap
        aboutFilterFunction()                           //过滤函数-filter
        aboutZipFunction()                              //合并函数-zip
        aboutFunctionalProgramming()                    //函数式编程
        aboutJavaAndNullable()                          //互操作性和可空性
    }

    private fun aboutAlsoFunction(){
//        public 公有
//        inline 高阶函数，以inline修饰
//        fun <T> 函数中声明一个泛型
//        T.apply 对该泛型进行函数扩展
//        block: T.() -> Unit 提供一个lambda参数,执行用户想要的操作,返回Unit,因为后面有return this,这里就不需要返回
//        block() = block(this) this默认省略,执行传入的lambda
//        return this 返回自身,可以链式调用
//        public inline fun <T> T.apply(block: T.() -> Unit): T {
//            contract {
//                callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//            }
//            block()
//            return this
//        }
    }

    private fun aboutDSL(){
        //Domain Specified Language 领域专用语言（json，gradle）
        //applyDSL函数就是DSL编程范式，定义输入输出规则
        //1.定义整个lambda输入规则：必须输入DSLClass这个类才能调用applyDSL，匿名函数中持有this和it
        //2.定义整个lambda输出规则：必须输出DSLClass本身
        //然后这里就可以根据DSL编程方式标准规则，写具体实现，这就是DSL编程范式
        val dslClass = DSLClass().applyDSL {
            toast("hello")
            toast(it)
            toast(info)
        }
    }

    private fun aboutTransferFunction(){
        val list = listOf("1","2","3")
        //["Nagisa","Nagisa","Nagisa"],原list不变
        val newList : List<String> = list.map {
            123
        }.map {
            true
        }.map {
            "Nagisa"
        }

        list.myListTransfer{
            123
        }
    }

    private fun aboutTransfer1Function(){
        //map：遍历每一个元素
        //flatMap：遍历每一个元素，并铺平元素(flatMap中的函数一定要返回一个Iterable，不然报错)
        val list =listOf(listOf(10,20),listOf(30,40),listOf(50,60))
        //[[10, 20], [30, 40], [50, 60]]
        val mapList = list.map{element->element.toString()}
        //[10, 20, 30, 40, 50, 60]
        val flatMapList = list.flatMap{element->element.asIterable()}
    }

    private fun aboutFilterFunction(){
        val list =listOf(listOf(15,20),listOf(25,30),listOf(35,40))
        //[20, 30, 40]
        list.flatMap {
            //it : [15,20],[25,30],[35,40]
            it.filter { value : Int ->
                //value : 15,20,25,30,35,40
                //返回true加入新集合，false不加入
                value % 2 == 0
            }
        }
    }

    private fun aboutZipFunction(){
        val list1 = listOf(15,20)
        val list2 = listOf(25,30)
        val list3 = listOf(35,40)
        //1.把第一个集合的元素做key，第二个集合的元素做value，合并两个集合
        //[(15, 25), (20, 30)]
        val newList1 = list1.zip(list2)
        //[((15, 25), 35), ((20, 30), 40)]
        val newList2 = list1.zip(list2).zip(list3)

        //2.长度匹配不上时，删除多余的key或者value
        //[(15, 25), (20, 30), (88, 66)]
        listOf(15,20,88,85).zip(listOf(25,30,66))
        //[(15, 25), (20, 30)]
        listOf(15,20).zip(listOf(25,30,66))
    }

    private fun aboutFunctionalProgramming(){
//        val nameList = listOf("1","2","3")
//        val ageList = listOf(1,2,3)
//        nameList.zip(ageList).toMap().map {
//            "${it.key},${it.value}"
//        }.map {
//            println(it)
//        }
        listOf("1","2","3").zip(listOf(1,2,3)).toMap().map {
            "${it.key},${it.value}"
        }.map {
            println(it)
        }
        /**
         *  List<String> nameList = new ArrayList<>();
            nameList.add("1");
            nameList.add("2");
            nameList.add("3");

            List<Integer> ageList = new ArrayList<>();
            ageList.add(1);
            ageList.add(2);
            ageList.add(3);

            Map<String,Integer> map = new HashMap<>();
            for (int i = 0; i < nameList.size(); i++) {
                map.put(nameList.get(i),ageList.get(i));
            }

            List<String> resultList = new ArrayList<>();
            for(String key : map.keySet()){
                resultList.add(key + "," + map.get(key));
            }

            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
         */
    }

    private fun aboutJavaAndNullable(){
        //java与kotlin交互的时候，java提供给kotlin的是String！这种类型
        //错误案例(空指针崩溃)
        println(JavaInteractiveClass().info.length)
        val clazz = JavaInteractiveClass()
        println(clazz.info.length)

        //解决方法1：碰到String！类型的处理都加上?.(如果漏加就会导致空指针崩溃)
        val info = JavaInteractiveClass().info
        println(info?.length)

        //解决方法2：直接用String？接收java传来的String！类型(下面不加?.编辑器会提示报错)
        val info1 : String? = JavaInteractiveClass().info
        println(info1?.length)
    }

}

class DSLClass{
    val info = "info"
    fun toast(str : String) = println(str)
}

inline fun DSLClass.applyDSL( lambda : DSLClass.(String) -> Unit) : DSLClass{
    lambda(info)
    return this
}

inline fun <I,O> List<I>.myListTransfer( lambda : List<I>.(I) -> O) : List<O>{
    val list : MutableList<O> = mutableListOf()
    for(item : I in this){
        val value : O = lambda(item)
        list.add(value)
    }
    return list.toList()
}