package com.excalibur.enjoylearning.jetpack.room.example3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.jetpack.room.RoomAppDatabase

class RoomExampleActivity3: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * getInstance时.addMigrations(MIGRATION_1_2)生效，数据库升级
         */
        val db = RoomAppDatabase.getInstance(this)
        val dao = db?.getRoomEntityDao()
        dao?.queryAll()?.forEach {
            Log.e("TestForCase", it.toString())
        }
    }

}