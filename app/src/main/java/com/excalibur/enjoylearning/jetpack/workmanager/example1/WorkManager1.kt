package com.excalibur.enjoylearning.jetpack.workmanager.example1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 1.WorkManger基础使用
class WorkManager1(context : Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        Log.e("TestForCase", "WorkManager1 start doWork...")
        try {
            Thread.sleep(5000)
        }catch (e: Exception){
            e.printStackTrace()
            return Result.failure()
        }finally {
            Log.e("TestForCase", "WorkManager1 finish doWork...")
        }
        return Result.success()
    }

}