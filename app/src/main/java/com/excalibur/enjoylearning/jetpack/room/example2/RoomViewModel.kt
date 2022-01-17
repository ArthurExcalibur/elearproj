package com.excalibur.enjoylearning.jetpack.room.example2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.excalibur.enjoylearning.jetpack.room.example1.RoomEntity

class RoomViewModel(application: Application): AndroidViewModel(application) {

    // 定义仓库 ViewModel只操作仓库
    private var roomDataRepository: RoomDataRepository? = null

    init {
        roomDataRepository = RoomDataRepository(application)
    }

    fun insertEntity(entities: Array<RoomEntity>) = roomDataRepository?.insertEntity(entities)

    fun deleteEntity(entity: RoomEntity) = roomDataRepository?.deleteEntity(entity)

    fun updateEntity(entity: RoomEntity) = roomDataRepository?.updateEntity(entity)

    fun queryAll(): LiveData<Array<RoomEntity>>? = roomDataRepository?.queryAll()

}