package com.excalibur.enjoylearning.io

import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

class AESUtils {

    companion object{
        const val DEFAULT_PWD = "abcdefghijklmnop"

        private const val algorithmStr = "AES/ECB/PKCS5Padding"

        private var encryptCipher: Cipher = Cipher.getInstance(algorithmStr)
        private var decryptCipher: Cipher = Cipher.getInstance(algorithmStr)

//        private fun initEncryptCipher() : Cipher{
//            return Cipher.getInstance(algorithmStr)
//        }
//
//        private fun initDecryptCipher() : Cipher{
//            return Cipher.getInstance(algorithmStr)
//        }

        fun doInit(password: String) {
            try {
                // 生成一个实现指定转换的 Cipher 对象。
//                encryptCipher = Cipher.getInstance(algorithmStr)
//                decryptCipher = Cipher.getInstance(algorithmStr) // algorithmStr
                val keyStr = password.toByteArray()
                val key = SecretKeySpec(keyStr, "AES")
                encryptCipher.init(Cipher.ENCRYPT_MODE, key)
                decryptCipher.init(Cipher.DECRYPT_MODE, key)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: NoSuchPaddingException) {
                e.printStackTrace()
            } catch (e: InvalidKeyException) {
                e.printStackTrace()
            }
        }

        /**
         *
         * @param srcAPKfile  源文件所在位置
         * @param dstApkFile  目标文件
         * @return             加密后的新dex 文件
         * @throws Exception
         */
        @Throws(Exception::class)
        fun encryptAPKFile(srcAPKfile: File?, dstApkFile: File): File? {
            if (srcAPKfile == null) {
                Log.e("TestForCase","encryptAPKFile :srcAPKFile null")
                return null
            }
//        File disFile = new File(srcAPKfile.getAbsolutePath() + "unzip");
//		Zip.unZip(srcAPKfile, disFile);
            ZipUtils.unZip(srcAPKfile, dstApkFile)
            //获得所有的dex （需要处理分包情况）
//            val dexFiles: Array<File> = dstApkFile.listFiles(object : FilenameFilter() {
//                fun accept(file: File?, s: String): Boolean {
//                    return s.endsWith(".dex")
//                }
//            })
            val dexFiles = dstApkFile.listFiles()?.filter {
                it?.endsWith(".dex") ?: false
            }
            var mainDexFile: File? = null
            var mainDexData: ByteArray? = null
            dexFiles?.forEach {
                //读数据
                val buffer: ByteArray = AllUtils.getBytes(it)
                //加密
                val encryptBytes: ByteArray? = encrypt(buffer)
                if (it.name.endsWith("classes.dex")) {
                    mainDexData = encryptBytes
                    mainDexFile = it
                }
                //写数据  替换原来的数据
                val fos = FileOutputStream(it)
                fos.write(encryptBytes)
                fos.flush()
                fos.close()
            }
            return mainDexFile
        }

        fun encrypt(content: ByteArray): ByteArray? {
            try {
                return encryptCipher.doFinal(content)
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            }
            return null
        }

        fun decrypt(content: ByteArray?): ByteArray? {
            try {
                return decryptCipher.doFinal(content)
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            }
            return null
        }


        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
//        byte[] newfs = Utils.int2Bytes(2312312);
//        byte[] refs = new byte[4];
//        for (int i = 0; i < 4; i++) {
//            refs[i] = newfs[newfs.length - 1 - i];
//        }
//        System.out.println(Arrays.toString(newfs));
//        System.out.println(Arrays.toString(refs));
//
//        ByteBuf byteBuf = Unpooled.buffer();
//
//        byteBuf.writeInt(2312312);
//        byte[] a = new byte[4];
//        byteBuf.order(ByteOrder.LITTLE_ENDIAN);
//        byteBuf.readBytes(a);
//        System.out.println(Arrays.toString(a));

//        AES.init(AES.DEFAULT_PWD);
//        String msg = Base64.encode(AES.encrypt(new byte[]{1, 2, 3, 4, 5}));
//        System.out.println(msg);
//        byte[] aes = AES.decrypt(Base64.decode(msg));
//        System.out.println(Arrays.toString(aes));
            val zip = File("/Users/xiang/develop/source.apk")
            val absolutePath = zip.absolutePath
            val dir = File(absolutePath.substring(0, absolutePath.lastIndexOf(".")))
            ZipUtils.unZip(zip, dir)
            val zip2 = File("/Users/xiang/develop/app-debug2.apk")
            ZipUtils.zipFile(dir, zip2)
            val argv = arrayOf(
                "jarsigner", "-verbose", "-sigalg", "MD5withRSA",
                "-digestalg", "SHA1",
                "-keystore", "/Users/xiang/develop/debug.keystore",
                "-storepass", "android",
                "-keypass", "android",
                "-signedjar", "/Users/xiang/develop/app-debug2-sign.apk",
                "/Users/xiang/develop/app-debug2.apk",
                "androiddebugkey"
            )
            var pro: Process? = null
            try {
                pro = Runtime.getRuntime().exec(argv)
                //destroy the stream
                try {
                    pro.waitFor()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            } finally {
                pro?.destroy()
            }
        }
    }

}