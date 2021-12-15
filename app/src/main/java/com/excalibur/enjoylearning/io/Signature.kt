package com.excalibur.enjoylearning.io

import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.RuntimeException

class Signature {

    companion object{
        @Throws(InterruptedException::class, IOException::class)
        fun signature(unsignedApk: File, signedApk: File) {
            val cmd = arrayOf(
                "cmd.exe", "/C ", "jarsigner", "-sigalg", "MD5withRSA",
                "-digestalg", "SHA1",
                "-keystore", "C:/Users/allen/.android/debug.keystore",
                "-storepass", "android",
                "-keypass", "android",
                "-signedjar", signedApk.absolutePath,
                unsignedApk.absolutePath,
                "androiddebugkey"
            )
            val process = Runtime.getRuntime().exec(cmd)
            Log.e("TestForCase","start sign")
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null)
//            System.out.println("tasklist: " + line);
            try {
                val waitResult = process.waitFor()
                Log.e("TestForCase","waitResult: $waitResult")
            } catch (e: InterruptedException) {
                e.printStackTrace()
                throw e
            }
            Log.e("TestForCase","process.exitValue() " + process.exitValue())
            if (process.exitValue() != 0) {
                val inputStream: InputStream = process.errorStream
                var len: Int
                val buffer = ByteArray(2048)
                val bos = ByteArrayOutputStream()
                while (inputStream.read(buffer).also { len = it } != -1) {
                    bos.write(buffer, 0, len)
                }
                Log.e("TestForCase",String(bos.toByteArray()))
                throw RuntimeException("签名执行失败")
            }
            Log.e("TestForCase","finish signed")
            process.destroy()
        }
    }

}