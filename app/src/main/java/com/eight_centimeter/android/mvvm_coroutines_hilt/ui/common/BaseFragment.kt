package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment", this::class.java.name)
    }
}
