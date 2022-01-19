package com.excalibur.enjoylearning.jetpack.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.excalibur.enjoylearning.R

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TestForCase", "Fragment1 onCreateView")
        return layoutInflater.inflate(R.layout.fra_nav1, null)
    }

}