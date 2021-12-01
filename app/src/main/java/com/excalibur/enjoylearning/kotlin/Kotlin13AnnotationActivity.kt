@file:JvmName("MyName")

package com.excalibur.enjoylearning.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Kotlin13AnnotationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutJvmNameAnnotation()                        //@file:JvmName("MyName")
        aboutJvmFieldAnnotation()                       //@JvmField
        aboutJvmOverloadsAnnotation()                   //@JvmOverloads
        aboutJvmStaticAnnotation()                      //@JvmStatic
    }

    private fun aboutJvmNameAnnotation(){
        //在编译器环节修改生成的类名
        //必须写在包名外部（最顶部）
    }

    private fun aboutJvmFieldAnnotation(){
        //把变量直接暴露给外部
        //不加：Java外部调用只能JvmFieldClassKt().getName()
        //加了：Java外部调用可以直接JvmFieldClassKt().name
    }

    private fun aboutJvmOverloadsAnnotation(){
        //把方法暴露给外部
        //不加：Java外部无法调用JvmFieldClassKt().show()
        //加了：Java外部可以调用JvmFieldClassKt().show()
    }

    private fun aboutJvmStaticAnnotation(){
        //将伴生对象里面的静态对象或者方法提到类内部当做静态对象或者方法暴露给外部

        //不加JvmField：Java外部:JvmFieldClass.Companion.info
        //加了JvmField：Java外部:JvmFieldClass.info

        //不加JvmStatic：Java外部:JvmFieldClass.Companion.showTag()
        //加了JvmStatic：Java外部:JvmFieldClass.showTag()
    }

}

/**
 *  不加注解
 *  public final class JvmFieldClass {
        @NotNull
        private final String name = "Nagisa";
        @NotNull
        private static final String info = "TAG";
        @NotNull
        public static final JvmFieldClass.Companion Companion = new JvmFieldClass.Companion((DefaultConstructorMarker)null);

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final void show(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            boolean var2 = false;
            System.out.println(str);
        }

        public static final class Companion {
            @NotNull
            public final String getInfo() {
                return JvmFieldClass.info;
            }

            public final void showTag() {
                String var1 = ((JvmFieldClass.Companion)this).getInfo();
                boolean var2 = false;
                System.out.println(var1);
            }

            private Companion() {
            }

            // $FF: synthetic method
            public Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
 */

/**
 *  加了那些注解之后
 *  public final class JvmFieldClass {
        @JvmField
        @NotNull
        public final String name = "Nagisa";
        @JvmField
        @NotNull
        public static final String info = "TAG";
        @NotNull
        public static final JvmFieldClass.Companion Companion = new JvmFieldClass.Companion((DefaultConstructorMarker)null);

        @JvmOverloads
        public final void show(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            boolean var2 = false;
            System.out.println(str);
        }

        @JvmStatic
        public static final void showTag() {
            Companion.showTag();
        }

        public static final class Companion {
            @JvmStatic
            public final void showTag() {
                String var1 = JvmFieldClass.info;
                boolean var2 = false;
                System.out.println(var1);
            }

            private Companion() {
            }

            // $FF: synthetic method
            public Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
 */
class JvmFieldClass{
    @JvmField
    val name = "Nagisa"
    @JvmOverloads
    fun show(str : String) = println(str)

    companion object{
        @JvmField
        val info = "TAG"
        @JvmStatic
        fun showTag() = println(info)
    }

}