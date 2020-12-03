package com.eight_centimeter.android.mvvm_coroutines_hilt.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.addFragment(containerRes: Int, getFragment: () -> Fragment, tag: String) {
    val oldFragment = findFragmentByTag(tag)
    beginTransaction().apply {
        if (oldFragment == null) {
            val newFragment = getFragment.invoke()
            add(containerRes, newFragment, tag)
            show(newFragment)
        } else {
            show(oldFragment)
        }
        commitAllowingStateLoss()
    }
}

fun FragmentManager.hideFragment(tag: String) {
    findFragmentByTag(tag)?.let {
        if (!it.isHidden) {
            val transaction = beginTransaction()
            transaction.hide(it)
            transaction.commit()
        }
    }
}

fun FragmentManager.removeFragment(tag: String) {
    findFragmentByTag(tag)?.let {
        if (!it.isHidden) {
            val transaction = beginTransaction()
            transaction.remove(it)
            transaction.commit()
        }
    }
}
