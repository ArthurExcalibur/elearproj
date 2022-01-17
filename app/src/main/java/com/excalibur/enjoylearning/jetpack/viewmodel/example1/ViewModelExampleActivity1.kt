package com.excalibur.enjoylearning.jetpack.viewmodel.example1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.excalibur.enjoylearning.R

class ViewModelExampleActivity1 : AppCompatActivity() , View.OnClickListener{

    private var phoneTv: TextView? = null

    private var phoneTv1: TextView? = null
    private var phoneTv2: TextView? = null
    private var phoneTv3: TextView? = null
    private var phoneTv4: TextView? = null
    private var phoneTv5: TextView? = null
    private var phoneTv6: TextView? = null
    private var phoneTv7: TextView? = null
    private var phoneTv8: TextView? = null
    private var phoneTv9: TextView? = null
    private var phoneTvXing: TextView? = null
    private var phoneTv0: TextView? = null
    private var phoneTvJing: TextView? = null

    private var clearTv: TextView? = null
    private var callTv: TextView? = null
    private var deleteTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_exaple1)
        initView()
    }

    private fun initView(){
        phoneTv = findViewById(R.id.phone_tv)
        phoneTv1 = findViewById(R.id.phone_1)
        phoneTv2 = findViewById(R.id.phone_2)
        phoneTv3 = findViewById(R.id.phone_3)
        phoneTv4 = findViewById(R.id.phone_4)
        phoneTv5 = findViewById(R.id.phone_5)
        phoneTv6 = findViewById(R.id.phone_6)
        phoneTv7 = findViewById(R.id.phone_7)
        phoneTv8 = findViewById(R.id.phone_8)
        phoneTv9 = findViewById(R.id.phone_9)
        phoneTvXing = findViewById(R.id.phone_xing)
        phoneTv0 = findViewById(R.id.phone_0)
        phoneTvJing = findViewById(R.id.phone_jing)
        clearTv = findViewById(R.id.phone_clear)
        callTv = findViewById(R.id.phone_call)
        deleteTv = findViewById(R.id.phone_delete)

        phoneTv1?.setOnClickListener(this)
        phoneTv2?.setOnClickListener(this)
        phoneTv3?.setOnClickListener(this)
        phoneTv4?.setOnClickListener(this)
        phoneTv5?.setOnClickListener(this)
        phoneTv6?.setOnClickListener(this)
        phoneTv7?.setOnClickListener(this)
        phoneTv8?.setOnClickListener(this)
        phoneTv9?.setOnClickListener(this)
        phoneTvXing?.setOnClickListener(this)
        phoneTv0?.setOnClickListener(this)
        phoneTvJing?.setOnClickListener(this)
        clearTv?.setOnClickListener(this)
        callTv?.setOnClickListener(this)
        deleteTv?.setOnClickListener(this)
    }

    private var phoneNumber: String = ""
    private fun appendNumber(number: String){
        phoneNumber += number
        phoneTv?.text = phoneNumber
    }

    private fun clear(){
        phoneNumber = ""
        phoneTv?.text = phoneNumber
    }

    private fun call(){
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    private fun back(){
        val length = phoneNumber.length
        if(length >= 1){
            phoneNumber = phoneNumber.substring(0, length - 1)
            phoneTv?.text = phoneNumber
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.phone_1 -> appendNumber("1")
            R.id.phone_2 -> appendNumber("2")
            R.id.phone_3 -> appendNumber("3")
            R.id.phone_4 -> appendNumber("4")
            R.id.phone_5 -> appendNumber("5")
            R.id.phone_6 -> appendNumber("6")
            R.id.phone_7 -> appendNumber("7")
            R.id.phone_8 -> appendNumber("8")
            R.id.phone_9 -> appendNumber("9")
            R.id.phone_xing -> appendNumber("*")
            R.id.phone_0 -> appendNumber("0")
            R.id.phone_jing -> appendNumber("#")
            R.id.phone_clear -> clear()
            R.id.phone_call -> call()
            R.id.phone_delete -> back()
        }
    }

}