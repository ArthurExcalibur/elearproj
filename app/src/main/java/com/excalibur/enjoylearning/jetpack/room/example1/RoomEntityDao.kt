package com.excalibur.enjoylearning.jetpack.room.example1

import androidx.room.*

@Dao
interface RoomEntityDao {

    @Insert
    fun insertEntity(vararg entity: RoomEntity)

    @Delete
    fun deleteEntity(entity: RoomEntity)

    @Update
    fun updateEntity(entity: RoomEntity)

    @Query("select * from RoomEntity")
    fun queryAll(): Array<RoomEntity>

    @Query("select * from RoomEntity where name like :name")
    fun queryEntityByName(name: String): Array<RoomEntity>

    @Query("select * from RoomEntity where id in(:ids)")
    fun queryEntityByIds(vararg ids: Int): Array<RoomEntity>

    @Query("select name from RoomEntity where id in(:ids)")
    fun queryName(vararg ids: Int): Array<RoomEntityPack>

}