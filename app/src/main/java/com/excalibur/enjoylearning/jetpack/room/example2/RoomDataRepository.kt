package com.excalibur.enjoylearning.jetpack.room.example2

import android.content.Context
import androidx.lifecycle.LiveData
import com.excalibur.enjoylearning.jetpack.room.RoomAppDatabase
import com.excalibur.enjoylearning.jetpack.room.example1.RoomEntity

class RoomDataRepository(context: Context) {

    lateinit var data: LiveData<Array<RoomEntity>>
    lateinit var roomEntityLivedataDao: RoomEntityLivedataDao

    init {
        val db = RoomAppDatabase.getInstance(context)
        roomEntityLivedataDao = db?.getRoomEntityLivedataDao()!!
    }

    fun insertEntity(entities: Array<RoomEntity>){
        roomEntityLivedataDao.insertEntity(entities)
    }

    fun deleteEntity(entity: RoomEntity){
        roomEntityLivedataDao.deleteEntity(entity)
    }

    fun updateEntity(entity: RoomEntity){
        roomEntityLivedataDao.updateEntity(entity)
    }

    fun queryAll(): LiveData<Array<RoomEntity>> = roomEntityLivedataDao.queryAll()

}