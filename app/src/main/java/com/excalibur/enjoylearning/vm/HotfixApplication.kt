package com.excalibur.enjoylearning.vm

import android.app.Application
import android.os.Environment
import android.util.Log
import java.io.File

class HotfixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        HotfixUtils().fix(this, File("sdcard/update.dex"))
    }

}