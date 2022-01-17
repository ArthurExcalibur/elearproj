package com.excalibur.enjoylearning.jetpack.room.example1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sex") val sex: String,
    //升级数据库测试
    @ColumnInfo(name = "flag") val flag: Int
)