package com.excalibur.enjoylearning.io

import java.io.*
import java.lang.Exception
import java.util.zip.*

class ZipUtils {

    companion object{
        @Throws(Exception::class)
        fun unZip(zip: File, dir: File){
            dir.delete()
            val zipFile = ZipFile(zip)
            val entities = zipFile.entries()
            while (entities.hasMoreElements()){
                val zipEntry : ZipEntry = entities.nextElement()
                val name : String = zipEntry.name
                if("META-INF/CERT.RSA" == name || "META-INF/CERT.SF" == name || "META-INF/MANIFEST.MF" == name){
                    continue
                }
                if(!zipEntry.isDirectory){
                    val file : File = File(dir,name)
                    if(file.parentFile != null && !file.parentFile.exists()){
                        file.parentFile.mkdirs()
                    }
                    val fos: FileOutputStream = FileOutputStream(file)
                    val inputStream : InputStream = zipFile.getInputStream(zipEntry)
                    val buffer = ByteArray(1024)
//                var length = inputStream.read(buffer)
//                while (length != -1){
//                    fos.write(buffer, 0, length)
//                    length = inputStream.read(buffer)
//                }
                    var length = 0
                    while (inputStream.read(buffer).also { length = it } != -1) {
                        fos.write(buffer, 0, length)
                    }
                    inputStream.close()
                    fos.close()
                }
            }
        }

        @Throws(Exception::class)
        fun zipFile(dir: File, zip : File){
            zip.delete()
            val checkedOutputStream = CheckedOutputStream(FileOutputStream(zip), CRC32())
            val zipOutputStream = ZipOutputStream(checkedOutputStream)
            compress(dir,zipOutputStream,"")
            zipOutputStream.flush()
            zipOutputStream.close()
            checkedOutputStream.close()
        }

        @Throws(Exception::class)
        private fun compress(dir: File, zipOutputStream: ZipOutputStream, basePath: String){
            if(dir.isDirectory)
                compressDirectory(dir,zipOutputStream,basePath)
            else
                compressFile(dir,zipOutputStream,basePath)
        }

        @Throws(Exception::class)
        private fun compressDirectory(dir: File, zipOutputStream: ZipOutputStream, basePath: String){
            val list = dir.listFiles() ?: return
            if(list.isEmpty()){
                val entity = ZipEntry(basePath + dir.name + "/")
                zipOutputStream.putNextEntry(entity)
                zipOutputStream.closeEntry()
            }
            list.forEach {
                if(it != null)
                    compressFile(it,zipOutputStream,basePath + dir.name + "/")
            }
        }

        @Throws(Exception::class)
        private fun compressFile(file: File, zipOutputStream: ZipOutputStream, dir: String){
            val name = dir + file.name
            val dirNew = name.split("/")
            val buffer = StringBuffer()
            if(dirNew.size > 1){
                dirNew.forEach {
                    buffer.append("/").append(it)
                }
            } else{
                buffer.append("/")
            }
            val entity = ZipEntry(buffer.toString().substring(1))
            zipOutputStream.putNextEntry(entity)
            val bufferedInputStream = BufferedInputStream(FileInputStream(file))
            val data = ByteArray(1024)
            var count = 0
            while (bufferedInputStream.read(data, 0, 1024).also { count = it } != -1) {
                zipOutputStream.write(data, 0, count)
            }
//        var count =  bufferedInputStream.read(data, 0, 1024)
//        while (count != -1){
//            zipOutputStream.write(data,0,count)
//            count =  bufferedInputStream.read(data, 0, 1024)
//        }
            bufferedInputStream.close()
            zipOutputStream.close()
        }
    }

}