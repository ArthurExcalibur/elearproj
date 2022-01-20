package com.excalibur.enjoylearning.jetpack.workmanager

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.excalibur.enjoylearning.R
import com.excalibur.enjoylearning.jetpack.workmanager.example1.WorkManager1
import com.excalibur.enjoylearning.jetpack.workmanager.example2.WorkManager2
import com.excalibur.enjoylearning.jetpack.workmanager.example3.WorkManager3
import com.excalibur.enjoylearning.jetpack.workmanager.example3.WorkManager4
import com.excalibur.enjoylearning.jetpack.workmanager.example3.WorkManager5
import com.excalibur.enjoylearning.jetpack.workmanager.example4.WorkManager6
import com.excalibur.enjoylearning.jetpack.workmanager.example5.WorkManager7
import com.excalibur.enjoylearning.jetpack.workmanager.example6.WorkManager8
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() , SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_main)
        backgroundTv = findViewById(R.id.activity_work_manager_tv)
        // 绑定 SP 变化监听
        val sp = getSharedPreferences("work_manager", MODE_PRIVATE)
        sp.registerOnSharedPreferenceChangeListener(this)
        updateUI()
    }

    fun singleRequest(v: View){
        /**
         * OneTimeWorkRequest 单个WorkManager任务
         *
         * WorkManager1 done doWork:androidx.work.impl.OperationImpl@e56fcaa（异步结果不能这样获取）
         * WorkManager1 start doWork...
         * WorkManager1 finish doWork...
         */
        val request: WorkRequest = OneTimeWorkRequest.Builder(WorkManager1::class.java).build()
        val result = WorkManager.getInstance(this).enqueue(request)
        Log.e("TestForCase", "WorkManager1 done doWork:$result")
    }

    fun withDataRequest(v: View){
        val data = Data.Builder().putString("inputData", "Hello WorkManager").build()
        val request: WorkRequest = OneTimeWorkRequest.Builder(WorkManager2::class.java).setInputData(data).build()
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this){
            Log.e("TestForCase", "WorkManager2 state:${it.state.name}")
            if(it.state.isFinished){
                Log.e("TestForCase", "WorkManager2 done doWork:${it.outputData.getString("outputData")}")
            }
        }
        WorkManager.getInstance(this).enqueue(request)
    }

    fun multiRequest(v: View){
        val request1: OneTimeWorkRequest = OneTimeWorkRequest.Builder(WorkManager3::class.java).build()
        val request2: OneTimeWorkRequest = OneTimeWorkRequest.Builder(WorkManager4::class.java).build()
        val request3: OneTimeWorkRequest = OneTimeWorkRequest.Builder(WorkManager5::class.java).build()
        /**
         * 链式调用，前一个任务成功后下一个才会执行（顺序执行）
         */
        WorkManager.getInstance(this)
            .beginWith(request1)
            .then(request2)
            .then(request3)
            .enqueue()

        /**
         * 另一种写法
         */
        val multiRequestList: MutableList<OneTimeWorkRequest> = ArrayList()
        multiRequestList.add(request1)
        multiRequestList.add(request2)
        WorkManager.getInstance(this)
            .beginWith(multiRequestList)
            .then(request3)
            .enqueue()
    }

    fun replyRequest(v: View){
        /**
         * 重复任务设置的时间不能小于十五分钟（如果小于，内部会自动修改为十五分钟）
         */
        val request = PeriodicWorkRequest.Builder(WorkManager6::class.java, 5, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this){
            Log.e("TestForCase", "WorkManager6 state:${it.state.name}")
        }
        WorkManager.getInstance(this).enqueue(request)
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    fun limitRequest(v: View){
        /**
         * setRequiredNetworkType   限制网络状态
         * setRequiresCharging      限制充电中
         * setRequiresBatteryNotLow 限制电量是否充足
         * setRequiresDeviceIdle    限制设备空闲
         * setRequiresStorageNotLow 限制可用存储是否地狱临界阈值
         */
        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
//            .setRequiresBatteryNotLow(true)
            .setRequiresDeviceIdle(true)
//            .setRequiresStorageNotLow(true)
            .build()
        val request = OneTimeWorkRequest
            .Builder(WorkManager7::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(request)
    }

    lateinit var backgroundTv: TextView
    fun backgroundRequest(v: View){
        /**
         * 这个测试点在于点击开始任务之后，即使杀死APP，下次进入APP时发现SP还是被更新了
         */
        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = OneTimeWorkRequest
            .Builder(WorkManager8::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(request)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) = updateUI()

    private fun updateUI(){
        val textString = getSharedPreferences("work_manager", MODE_PRIVATE).getInt("work", 0)
        backgroundTv.text = textString.toString()
    }
    fun clearSp(v: View){
        getSharedPreferences("work_manager", MODE_PRIVATE)
            .edit()
            .putInt("work", 0)
            .apply()
    }

}