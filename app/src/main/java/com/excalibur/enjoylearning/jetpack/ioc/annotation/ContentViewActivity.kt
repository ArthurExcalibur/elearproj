package com.excalibur.enjoylearning.jetpack.ioc.annotation

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.R
import com.idazoo.ioc_annotation_lib.annotation.BindView
import com.idazoo.ioc_annotation_lib.annotation.ContentView
import com.idazoo.ioc_annotation_lib.annotation.InjectTool
import com.idazoo.ioc_annotation_lib.annotation.OnClick
import com.idazoo.ioc_annotation_lib.annotation_commmon.CommonInjectTool
import com.idazoo.ioc_annotation_lib.annotation_commmon.OnClickCommon
import com.idazoo.ioc_annotation_lib.annotation_commmon.OnLongClickCommon

@ContentView(R.layout.activity_jetpack_ioc_annotation)
class ContentViewActivity : AppCompatActivity() {

    @BindView(R.id.btn)
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InjectTool.inject(this)
        CommonInjectTool.injectCommon(this)

    }

//    @OnClick(R.id.btn)
//    fun showBtn() = Toast.makeText(this, "showBtn", Toast.LENGTH_SHORT).show()

    @OnClickCommon(R.id.btn)
    fun showBtn1() = Toast.makeText(this, "showBtn1", Toast.LENGTH_SHORT).show()

    @OnLongClickCommon(R.id.btn)
    fun showBtn2() : Boolean{
        Toast.makeText(this, "showBtn2", Toast.LENGTH_SHORT).show()
        return true
    }

}