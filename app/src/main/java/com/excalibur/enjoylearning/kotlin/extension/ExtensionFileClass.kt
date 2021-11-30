package com.excalibur.enjoylearning.kotlin.extension

//TODO kotlin中的扩展文件（写在独立文件中的扩展函数）
//TODO 示例项目中，可以把很多扩展操作写到一起，便于集中管理和外部调用
class ExtensionFileClass {

}

//1.扩展文件一般都是public（如果是private会导致外部无法调用）
//2.Iterable是list和set的父类，扩展它所以子类都可以调用
//3.案例的扩展函数作用在于随即取集合中的一个元素返回
fun <T> Iterable<T>.randomGetValue() = this.shuffled().first()
fun <T> Iterable<T>.randomGetValuePrint() = println(this.shuffled().first())