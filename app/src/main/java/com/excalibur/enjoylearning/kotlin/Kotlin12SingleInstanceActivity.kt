package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Kotlin12SingleInstanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutSingleInstance()                           //单例模式
    }

    private fun aboutSingleInstance(){
        SingleInstanceClass.printLog()
        SingleInstanceClass1.get().printLog()
        SingleInstanceClass2.get().printLog()
        SingleInstanceClass3.instance.printLog()
        SingleInstanceClass4.instance.printLog()
    }

}

//饿汉式单例（一开始就初始化）
/**
 *  public static class SingleInstance {
        private static SingleInstance instance = new SingleInstance();
        private SingleInstance(){ }
        public static SingleInstance getInstance(){
            return instance;
        }
    }
 */
object SingleInstanceClass{
    fun printLog() = println("log")
}

//懒汉式单例（用到的时候再初始化）
/**
 *  public static class SingleInstance{
        private static SingleInstance instance;
        private SingleInstance(){ }
        public static SingleInstance getInstance(){
            if(instance == null)
                instance = new SingleInstance();
            }
            return instance;
        }
    }
 */
class SingleInstanceClass1{
    companion object{
        private var instance : SingleInstanceClass1? = null
            get() {
                if(field == null)
                    field = SingleInstanceClass1()
                return field
            }
        //不能用getInstance作为为方法名，因为在伴生对象声明时，内部已有getInstance方法
        fun get() = instance!!
    }
    fun printLog() = println("log")
}

//线程安全懒汉式单例（使用同步锁）
/**
 *  public static class SingleInstance{
        private static SingleInstance instance;
        private SingleInstance(){}
        public static synchronized SingleInstance getInstance(){//使用同步锁
            if(instance==null){
                instance=new SingleInstance();
            }
            return instance;
        }
    }
 */
class SingleInstanceClass2{
    companion object{
        private var instance : SingleInstanceClass1? = null
            get() {
                if(field == null)
                    field = SingleInstanceClass1()
                return field
            }
        @Synchronized
        fun get() = instance!!
    }
    fun printLog() = println("log")
}

//线程安全懒汉式单例（双重校验锁式）
/**
 *  public static class SingleInstance{
        private volatile static SingletonDemo instance;
        private SingletonDemo(){}
        public static SingletonDemo getInstance(){
            if(instance==null){
                synchronized (SingletonDemo.class){
                    if(instance==null){
                        instance=new SingletonDemo();
                    }
                }
            }
            return instance;
        }
    }
 */
class SingleInstanceClass3 private constructor(){
    companion object {
        val instance: SingleInstanceClass3 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingleInstanceClass3()
        }
    }
    fun printLog() = println("log")
}

//静态内部类式
/**
 *  public static class SingleInstance{
        private static class SingletonHolder{
            private static SingleInstance instance = new SingleInstance();
        }
        private SingleInstance(){}
        public static SingleInstance getInstance(){
            return SingletonHolder.instance;
        }
    }
 */
class SingleInstanceClass4 private constructor(){
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder= SingleInstanceClass4()
    }
    fun printLog() = println("log")
}