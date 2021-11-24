package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO kotlin中的集合list，set和数组array和map
class Kotlin6ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutList()                         //list的申明和元素获取
        aboutList1()                        //可变list
        aboutListMutator()                  //list的mutator
        aboutListForeach()                  //集合遍历（list，set）
        aboutItemsFilter()                  //解构语法和集合过滤元素
        aboutSet()                          //set的申明和元素获取
        aboutSet1()                         //可变set
        aboutSetMutator()                   //set的mutator
        aboutTransfer()                     //list和set和array互相转换
        aboutArray()                        //数组array的申明和元素获取
        aboutMap()                          //map的申明和元素获取
        aboutMap1()                         //可变map
    }

    private fun aboutList(){
        val list1 : List<String> = listOf<String>("111","222","333333333")
        val list2 : List<String> = listOf("111","222","333333333")
        val list3 = listOf("111","222","333333333")

        list3[1]//下标越界风险

        list3.getOrElse(1){"222"}//下标越界返回222
        list3.getOrNull(1)//下标越界返回null
        list3.getOrNull(100) ?: "333"//下标越界返回333
    }

    private fun aboutList1(){
        val list = mutableListOf("1111","2222")
        list.add("111")
        list.removeAt(1)

        //不可变集合---->可变集合
        val list3 = listOf("111","222","333333333")
        list3.toMutableList()

        //可变集合---->不可变集合
        list.toList()
    }

    private fun aboutListMutator(){
        val list = mutableListOf("1111","2222")
        list += "dx"
        list += "nagisa"
        list -= "1111"
//        list.removeIf {
//            it == "1111"
//        }
    }

    private fun aboutListForeach(){
        val list = mutableListOf("1111","2222")
        for(str in list){

        }
        list.forEach {

        }
        list.forEachIndexed { index, str ->

        }

        val map : Map<String,String> = mapOf("key1" to "value1", "key2" to "value2")
        map.forEach {
            it.key
            it.value
        }
//        map.forEach { key, value ->
//
//        }
        map.forEach { (key, value) ->
            key
            value
        }
        for(item in map){
            item.key
            item.value
        }
        for(key in map.keys){
            map.getOrElse(key){"default"}
        }
    }

    private fun aboutItemsFilter(){
        val list = mutableListOf("1111","2222")
        val (value1,value2) = list
        println(value1)
        //_用来过滤解构赋值
        val (_, value) = list
        println(value)
    }

    private fun aboutSet(){
        val set1 : Set<String> = setOf<String>("111","222","333333333")
        val set2 : Set<String> = setOf("111","222","333333333")
        val set3 = setOf("111","222","333333333")

        set3.elementAt(1)//下标越界风险

        set3.elementAtOrElse(1){"222"}//下标越界返回222
        set3.elementAtOrNull(1)//下标越界返回null
        set3.elementAtOrNull(100) ?: "333"//下标越界返回333
    }

    private fun aboutSet1(){
        val set3 = mutableSetOf("111","222","333333333")
        set3.add("111")
        set3.remove("111")

        //不可变集合---->可变集合
        val set = setOf("111","222","333333333")
        set.toMutableSet()

        //可变集合---->不可变集合
        set3.toSet()
    }

    private fun aboutSetMutator(){
        val set3 = mutableSetOf("111","222","333333333")
        set3 += "dx"
        set3 += "nagisa"
        set3 -= "1111"
//        set3.removeIf {
//            it == "1111"
//        }
    }

    private fun aboutTransfer(){
        val list = mutableListOf("1111","2222")
        val set = mutableSetOf(1,2,3,4)
        val array = intArrayOf(1,2,3,4)

        /**
         * list《=====》set
         */
        list.toSet()//去重
        list.toMutableSet()
        list.toSet().toList()//去重然后返回新的list
        list.distinct()//去重：toMutableSet().toList()
        set.toList()
        set.toMutableList()

        /**
         * 集合《=====》数组
         */
        list.toTypedArray()
        set.toIntArray()
        array.toList()
        array.toSet()
    }

    private fun aboutArray(){
        //IntArray,DoubleArray,LongArray,ShortArray,ByteArray,FloatArray,BooleanArray,Array
        //没有StringArray
        val array1 : IntArray = intArrayOf(1,2,3,4)
        val array3 = intArrayOf(1,2,3,4)

        array3[1]//下标越界风险

        array3.elementAtOrElse(1){222}//下标越界返回222
        array3.elementAtOrNull(1)//下标越界返回null
        array3.elementAtOrNull(100) ?: 222//下标越界返回222
    }

    private fun aboutMap(){
        val map1 : Map<String,String> = mapOf("key1" to "value1", "key2" to "value2")
        val map2 = mapOf(Pair("key1","value1"), Pair("key2","value2"))

        map1.get("key1")
        map1["key1"]//返回值或者null

        //map1.getOrDefault("key1","2222")//下标越界返回222
        map1.getOrElse("key1"){"1111"}//下标越界返回1111

        map1.getValue("key1")//找不到值会崩溃
    }

    private fun aboutMap1(){
        val map1 : MutableMap<String,String> = mutableMapOf("key1" to "value1", "key2" to "value2")

        map1.put("","");
        map1 += "" to ""
        map1 -= ""
        map1 += Pair("","")
        map1["key1"] = ""

        //如果集合中没有这个key，将key，value放到集合中，再返回value
        //如果集合中有这个key，直接返回map中对应的value
        map1.getOrPut("",){""}
    }

}