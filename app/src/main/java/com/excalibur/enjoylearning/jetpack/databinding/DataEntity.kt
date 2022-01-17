package com.excalibur.enjoylearning.jetpack.databinding

import androidx.databinding.ObservableField

class DataEntity {
    val name: ObservableField<String> by lazy { ObservableField<String>() }
    val pass: ObservableField<String> by lazy { ObservableField<String>() }
}