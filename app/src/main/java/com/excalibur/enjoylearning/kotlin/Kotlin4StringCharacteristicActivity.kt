package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//TODO 关于kotlin中String的操作
class Kotlin4StringCharacteristicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutSubstring()//substring
        aboutSplit()//split
        aboutReplace()//replace
        aboutEquals()//equals和==和===
        aboutForeach()//遍历
    }

    private fun aboutSubstring(){
        val info : String = ""
        println(info.substring(0,info.indexOf("")))
        println(info.substring(0 until info.indexOf("")))//KT 基本用这个
    }

    private fun aboutSplit(){
        val info : String = ""
        val list = info.split("")//自动类型推断为List<String>
        println(list)
        val (p1,p2,p3,p4) = info.split("")
        println("$p1,$p2,$p3,$p4")
    }

    private fun aboutReplace(){
        val info : String = "11111111"
        //1
        info.replace("","")
        //2
        info.replace("","",false)
        //3
        info.replace(Regex("[abcd]"),"")
        //4
        //第一个参数是正则，第二个是lambda
        //作用在于将符合条件的char执行lambda中的操作后返回（比如用于加密：将string中所有的abcd执行各种操作）
        val afterReplace = info.replace(Regex("[abcd]")){
//            it                      ->    MatchResult
//            it.value                ->    String
//            it.groupValues          ->    List<String>
//            it.range                ->    IntRange
//            it.next()               ->    MatchResult?
//            it.destructured         ->    MatchResult.
//            it.groups               ->    MatchGroupCollection
            when(it.value){
                "a" -> "-"
                "b" -> "@"
                "c" -> "#"
                "d" -> "$"
                else -> it.value
            }
        }
    }

    private fun aboutEquals(){
        //==比较的是内容，和java的equals相同
        //===比较的是引用，和java==相同
        val name : String = "Dx"
        val name1 : String = "Dx"
        val name2 : String = "EX"
        //这两个是等价的
        println(name == name1)              //true
        println(name.equals(name1))         //true
        //比较引用地址（结合常量池）
        println(name === name1)             //true
        println(name1 === name2)            //false

        val name3 = "dx".capitalize()
        println(name === name3)             //false
    }

    private fun aboutForeach(){
        val name : String = "Dx"
        name.forEach {
            print(it)
        }
    }
}