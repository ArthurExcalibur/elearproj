package com.excalibur.enjoylearning.io

import android.util.Log
import java.io.*
import java.lang.RuntimeException

class DexUtils {

    companion object{
        @Throws(IOException::class, InterruptedException::class)
        fun jar2Dex(aarFile: File): File {
            val fakeDex = File(aarFile.parent + File.separator.toString() + "temp")
            Log.e("TestForCase","jar2Dex: aarFile.getParent(): " + aarFile.parent)
            //解压aar到 fakeDex 目录下
            ZipUtils.unZip(aarFile, fakeDex)
            //过滤找到对应的fakeDex 下的classes.jar
//            val files: Array<File> = fakeDex.listFiles(object : FilenameFilter() {
//                fun accept(file: File?, s: String): Boolean {
//                    return s == "classes.jar"
//                }
//            })
            val files = fakeDex.listFiles()?.filter {
                it?.name == "classes.jar"
            }
            if (files == null || files.isEmpty()) {
                throw RuntimeException("the aar is invalidate")
            }
            val classesJar: File = files[0]
            // 将classes.jar 变成classes.dex
            val aarDex = File(classesJar.parentFile, "classes.dex")

            //我们要将jar 转变成为dex 需要使用android tools 里面的dx.bat
            //使用java 调用windows 下的命令
            dxCommand(aarDex, classesJar)
            return aarDex
        }

        @Throws(IOException::class, InterruptedException::class)
        fun dxCommand(aarDex: File, classes_jar: File) {
            val runtime = Runtime.getRuntime()
            val process = runtime.exec(
                "cmd.exe /C dx --dex --output=" + aarDex.absolutePath.toString() + " " +
                        classes_jar.absolutePath
            )
            try {
                process.waitFor()
            } catch (e: InterruptedException) {
                e.printStackTrace()
                throw e
            }
            if (process.exitValue() != 0) {
                val inputStream: InputStream = process.errorStream
                var len: Int
                val buffer = ByteArray(2048)
                val bos = ByteArrayOutputStream()
                while ((inputStream.read(buffer).also { len = it }) != -1) {
                    bos.write(buffer, 0, len)
                }
                Log.e("TestForCase",String(bos.toByteArray()))
                throw RuntimeException("dx run failed")
            }
            process.destroy()
        }
    }

}