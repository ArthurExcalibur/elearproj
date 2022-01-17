package com.excalibur.enjoylearning.jetpack.room.example1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.jetpack.room.RoomAppDatabase

class RoomExampleActivity1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread{
            run {
                Thread.sleep(1000)
                val db = RoomAppDatabase.getInstance(this@RoomExampleActivity1)
                val dao = db?.getRoomEntityDao()
                for (i in 1..5){
                    val entity = RoomEntity(i, "name$i", "sex$i", 1)
                    dao?.insertEntity(entity)
                }
                Log.e("TestForCase", "insert success")
                Thread.sleep(3000)
                dao?.queryAll()?.forEach {
                    Log.e("TestForCase", it.toString())
                }
            }
        }.start()
    }

}