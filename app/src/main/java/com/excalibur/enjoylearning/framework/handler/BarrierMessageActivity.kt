package com.excalibur.enjoylearning.framework.handler

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.excalibur.enjoylearning.R

class BarrierMessageActivity : AppCompatActivity() {

    private var token : Int = 0
    private var handler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barrier_message)
        initHandler()
        /**
         * 主线程postBarrierMessage会导致主线程阻塞（而且此测试环境下点击事件也不起作用了，发送移除消息也发不出去）
         */

        /**
         * postMessage                  postMessage                 收到普通消息
         * postSycMessage               postSycMessage              收到异步消息
         * postBarrierMessage           postBarrierMessage
         * postMessage                  postMessage
         * postSycMessage               postSycMessage              收到异步消息
         * postRemoveBarrierMessage     postRemoveBarrierMessage    收到普通消息
         */

        /**
         * val p = Runnable()
         * p.run还是执行在主线程
         * Thread(p).start()才是子线程
         */
    }

    fun postMessage(v : View){
        postMessage()
    }
    fun postSycMessage(v : View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            postSyncMessage()
        }
    }
    fun postBarrierMessage(v : View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            postBarrierMessage()
        }
    }
    fun postRemoveBarrierMessage(v : View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            postRemoveBarrierMessage()
        }
    }


    private fun initHandler(){
        val p = Runnable {
            Looper.prepare()
            handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    //super.handleMessage(msg);
                    if (msg.what === 1) {
                        Log.e("TestForCase", "收到普通消息")
                    } else if (msg.what === 2) {
                        Log.e("TestForCase", "收到异步消息")
                    }
                }
            }
            Looper.loop()
        }
        Thread(p).start()
    }

    private fun postMessage(){
        Log.e("TestForCase","postMessage")
        val message = Message.obtain()
        message.what = 1
        handler?.sendMessage(message)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun postSyncMessage(){
        Log.e("TestForCase","postSyncMessage")
        val message = Message.obtain()
        message.what = 2
        message.isAsynchronous = true
        handler?.sendMessage(message)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun postBarrierMessage(){
        handler?.let {
            val queue = it.looper.queue
            val method = MessageQueue::class.java.getDeclaredMethod("postSyncBarrier")
            method.isAccessible = true
            token = method.invoke(queue) as Int
            Log.e("TestForCase","postBarrierMessage:{$token}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun postRemoveBarrierMessage(){
        handler?.let {
            Log.e("TestForCase","postRemoveBarrierMessage:{$token}")
            val queue = it.looper.queue
            val method = queue::class.java.getDeclaredMethod("removeSyncBarrier",Int::class.java)
            method.isAccessible = true
            method.invoke(queue,token)
        }
    }

}