package com.excalibur.enjoylearning.jetpack.workmanager.example6

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 6.后台WorkManger
class WorkManager8(context : Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        Log.e("TestForCase", "WorkManager8 start doWork...")
        try {
            Thread.sleep(5000)
        }catch (e: Exception){
            e.printStackTrace()
        }
        val sp = applicationContext.getSharedPreferences("work_manager", Context.MODE_PRIVATE)
        var spIntValue = sp.getInt("work", 0)
        sp.edit().putInt("work", ++spIntValue).apply()
        Log.d("TestForCase", "WorkManager8 finish doWork...")
        return Result.success()
    }

}