package com.excalibur.enjoylearning.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.migration.Migration
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

import androidx.room.RoomDatabase
import com.excalibur.enjoylearning.jetpack.room.example1.RoomEntity
import com.excalibur.enjoylearning.jetpack.room.example1.RoomEntityDao
import com.excalibur.enjoylearning.jetpack.room.example2.RoomEntityLivedataDao


@Database(entities = [RoomEntity::class], version = 2, exportSchema = false)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun getRoomEntityDao(): RoomEntityDao?

    abstract fun getRoomEntityLivedataDao(): RoomEntityLivedataDao?

    companion object {
        private var instance: RoomAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomAppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomAppDatabase::class.java, "DerryDB"
                ) // 可以强制在主线程运行数据库操作
                    .allowMainThreadQueries()
                    // 暴力升级 不管三七二十一 强制执行（数据会丢失）(慎用)
                    // .fallbackToDestructiveMigration()
                    // 稳定的方式升级
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return instance
        }

        // 下面是稳定升级的方式
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 在这里用SQL脚本完成数据的变化
                database.execSQL("alter table RoomEntity add column flag integer not null default 1")
            }
        }

        // ROOM 是不能降级的，我非要删除一个字段，却要保证数据的稳定性，这个是特殊情况
        // 特殊手法降级
        val MIGRATION_4_5: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // SQL 四步法

                // 1.先建立临时表
                //  database.execSQL("create table student_temp (uid integer primary key not null,name text,pwd text,addressId)");

                // 2.把之前表的数据（SQL语句的细节，同学们可以上网查询下）
                // database.execSQL("insert into student_temp (uid,name,pwd,addressid) " + " select uid,name,pwd,addressid from student");

                // 3.删除student 旧表
                // database.execSQL("drop table student");

                // 4.修改 临时表 为 新表 student
                // database.execSQL("alter table student_temp rename to student");
            }
        }
    }
}