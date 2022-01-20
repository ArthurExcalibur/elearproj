package com.excalibur.enjoylearning.jetpack.workmanager.example2

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 2.和WorkManger有数据交互
class WorkManager2(context : Context, private val workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        // TODO 接收数据
        val dataString = workerParameters.inputData.getString("inputData")
        Log.e("TestForCase", "WorkManager2 receive data:$dataString")

        // TODO 反馈数据(Result.Success成功，Result.Failure失败，Result.Retry重试一次（重试之后不管））
        val data: Data = Data.Builder().putString("outputData", "WorkManager2 result").build()
        return Result.Success(data)
    }

}