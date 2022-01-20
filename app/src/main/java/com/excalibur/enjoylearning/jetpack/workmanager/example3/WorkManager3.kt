package com.excalibur.enjoylearning.jetpack.workmanager.example3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 3.多个WorkManger一起执行
class WorkManager3(context : Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        Log.e("TestForCase", "WorkManager3 start doWork...")
        try {
            Thread.sleep(2000)
        }catch (e: Exception){
            e.printStackTrace()
            return Result.failure()
        }finally {
            Log.e("TestForCase", "WorkManager3 finish doWork...")
        }
        return Result.success()
    }

}