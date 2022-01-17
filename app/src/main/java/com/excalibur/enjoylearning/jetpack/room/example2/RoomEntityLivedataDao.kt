package com.excalibur.enjoylearning.jetpack.room.example2

import androidx.lifecycle.LiveData
import androidx.room.*
import com.excalibur.enjoylearning.jetpack.room.example1.RoomEntity

@Dao
interface RoomEntityLivedataDao {

    @Insert
    fun insertEntity(entities: Array<RoomEntity>)

    @Delete
    fun deleteEntity(entity: RoomEntity)

    @Update
    fun updateEntity(entity: RoomEntity)

    /**
     * 这里必须关联Livedata之后才能和viewmodel交流
     */
    @Query("select * from RoomEntity")
    fun queryAll(): LiveData<Array<RoomEntity>>

}