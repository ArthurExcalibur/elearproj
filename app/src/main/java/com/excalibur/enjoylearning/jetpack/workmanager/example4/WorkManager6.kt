package com.excalibur.enjoylearning.jetpack.workmanager.example4

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 4.重复WorkManger（轮循）
class WorkManager6(context : Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        Log.e("TestForCase", "WorkManager6 start doWork...")
        try {
            Thread.sleep(2000)
        }catch (e: Exception){
            e.printStackTrace()
            return Result.failure()
        }finally {
            Log.e("TestForCase", "WorkManager6 finish doWork...")
        }
        return Result.success()
    }

}