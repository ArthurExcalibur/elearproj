package com.excalibur.enjoylearning.io

import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.zip.Adler32

object AllUtils {

    //  public static byte[] int2Bytes(int number) {
    //      byte[] b = new byte[4];
    //      for (int i = 3; i >= 0; i--) {
    //          b[i] = (byte) (number % 256);
    //          number >>= 8;
    //      }
    //      return b;
    //  }

    fun int2Bytes(value: Int): ByteArray {
        val src = ByteArray(4)
        src[3] = (value shr 24 and 0xFF).toByte()
        src[2] = (value shr 16 and 0xFF).toByte()
        src[1] = (value shr 8 and 0xFF).toByte()
        src[0] = (value and 0xFF).toByte()
        return src
    }

    fun bytes2Int(src: ByteArray): Int {
        return (src[0].toInt() and 0xFF
                or (src[1].toInt() and 0xFF shl 8)
                or (src[2].toInt() and 0xFF shl 16)
                or (src[3].toInt() and 0xFF shl 24))
    }

    @Throws(NoSuchAlgorithmException::class)
    fun changeSignature(newDex: ByteArray) {
        Log.e("TestForCase","更换dex文件 签名信息...")
        val md = MessageDigest.getInstance("SHA-1")
        //从32个字节开始 计算sha1值
        md.update(newDex, 32, newDex.size - 32)
        val sha1 = md.digest()
        //从第12位开始拷贝20字节内容
        //替换signature
        System.arraycopy(sha1, 0, newDex, 12, 20)
        Log.e("TestForCase","更换dex文件 checksum...")
    }

    fun changeCheckSum(newDex: ByteArray) {
        val adler = Adler32()
        adler.update(newDex, 12, newDex.size - 12)
        val value = adler.value.toInt()
        val checkSum = int2Bytes(value)
        System.arraycopy(checkSum, 0, newDex, 8, 4)
    }

    @Throws(Exception::class)
    fun getBytes(dexFile: File?): ByteArray {
        val fis = RandomAccessFile(dexFile, "r")
        val buffer = ByteArray(fis.length().toInt())
        fis.readFully(buffer)
        fis.close()
        return buffer
    }

    fun changeFileSize(mainDexData: ByteArray, newDex: ByteArray, aarData: ByteArray?) {
        val bytes = int2Bytes(mainDexData.size)
        Log.e("TestForCase","拷贝原来dex长度到新的dex:" + bytes2Int(bytes))

        //更该文件头长度信息
        //修改
        Log.e("TestForCase","更换dex 文件头长度信息...")
        val fileSize = int2Bytes(newDex.size)
        System.arraycopy(fileSize, 0, newDex, 32, 4)
    }

    fun en(){
        var mainDexData: ByteArray //存储源apk中的源dex文件
        var aarData: ByteArray // 存储壳中的壳dex文件
        var mergeDex: ByteArray // 存储壳dex 和源dex 的合并的新dex文件

        val tempFileApk = File("source/apk/temp")
        if (tempFileApk.exists()) {
            val files = tempFileApk.listFiles()
            files?.forEach {
                if (it?.isFile == true) {
                    it.delete()
                }
            }
//            for (file in files) {
//                if (file.isFile) {
//                    file.delete()
//                }
//            }
        }

        val tempFileAar = File("source/aar/temp")
        if (tempFileAar.exists()) {
            val files = tempFileAar.listFiles()
//            for (file in files) {
//                if (file.isFile) {
//                    file.delete()
//                }
//            }
            files?.forEach {
                if (it?.isFile == true) {
                    it.delete()
                }
            }
        }

        /**
         * 第一步 处理原始apk 加密dex
         *
         */
        /**
         * 第一步 处理原始apk 加密dex
         *
         */
        AESUtils.doInit(AESUtils.DEFAULT_PWD)
        //解压apk
        //解压apk
        val apkFile = File("source/apk/app-debug.apk")
        val newApkFile = File(apkFile.parent + File.separator + "temp")
        if (!newApkFile.exists()) {
            newApkFile.mkdirs()
        }
        val mainDexFile: File? = AESUtils.encryptAPKFile(apkFile, newApkFile)
        if (newApkFile.isDirectory) {
            val listFiles = newApkFile.listFiles()
            for (file in listFiles) {
                if (file.isFile) {
                    if (file.name.endsWith(".dex")) {
                        val name = file.name
                        Log.e("TestForCase","rename step1:$name")
                        val cursor = name.indexOf(".dex")
                        val newName =
                            file.parent + File.separator + name.substring(0, cursor) + "_" + ".dex"
                        Log.e("TestForCase","rename step2:$newName")
                        file.renameTo(File(newName))
                    }
                }
            }
        }


        /**
         * 第二步 处理aar 获得壳dex
         */
        /**
         * 第二步 处理aar 获得壳dex
         */
        val aarFile = File("source/aar/mylibrary-debug.aar")
        val aarDex: File = DexUtils.jar2Dex(aarFile)
//        aarData = Utils.getBytes(aarDex);   //将dex文件读到byte 数组


//        aarData = Utils.getBytes(aarDex);   //将dex文件读到byte 数组
        val tempMainDex = File(newApkFile.path + File.separator + "classes.dex")
        if (!tempMainDex.exists()) {
            tempMainDex.createNewFile()
        }
//        System.out.println("MyMain" + tempMainDex.getAbsolutePath());
//        System.out.println("MyMain" + tempMainDex.getAbsolutePath());
        val fos = FileOutputStream(tempMainDex)
        val fbytes: ByteArray = getBytes(aarDex)
        fos.write(fbytes)
        fos.flush()
        fos.close()


        /**
         * 第3步 打包签名
         */
        /**
         * 第3步 打包签名
         */
        val unsignedApk = File("result/apk-unsigned.apk")
        unsignedApk.parentFile.mkdirs()
//        File disFile = new File(apkFile.getAbsolutePath() + File.separator+ "temp");
//        File disFile = new File(apkFile.getAbsolutePath() + File.separator+ "temp");
        ZipUtils.zipFile(newApkFile, unsignedApk)
        //不用插件就不能自动使用原apk的签名...
        //不用插件就不能自动使用原apk的签名...
        val signedApk = File("result/apk-signed.apk")
        Signature.signature(unsignedApk, signedApk)
    }

    private fun getMainDexFile(apkFile: File): File? {
        val disFile = File(apkFile.absolutePath + "unzip")
        ZipUtils.unZip(apkFile, disFile)
        val files = disFile.listFiles()?.filter {
            it?.name?.endsWith(".dex") ?: false
        }
        files?.forEach {
            if (it?.name?.endsWith("classes.dex") == true) {
                return it
            }
        }
//        val files: Array<File> = disFile.listFiles(object : FilenameFilter() {
//            fun accept(dir: File?, name: String): Boolean {
//                return if (name.endsWith(".dex")) {
//                    true
//                } else false
//            }
//        })
//        for (file in files) {
//            if (file.name.endsWith("classes.dex")) {
//                return file
//            }
//        }
        return null
    }

}