package com.eight_centimeter.android.mvvm_coroutines_hilt.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun dismissKeyboardIfTouchOutside(activity: Activity, event: MotionEvent?) {
    event?.let { dismissKeyboardIfTouchOutside(activity, it, null) }
}

fun dismissKeyboardIfTouchOutside(
    activity: Activity,
    event: MotionEvent,
    onDismissKeyboardListener: OnDismissKeyboardListener?
) {
    if (event.action == MotionEvent.ACTION_DOWN) {
        val focusView = activity.currentFocus
        if (focusView is EditText) {
            val outRect = Rect()
            focusView.getGlobalVisibleRect(outRect)
            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                focusView.clearFocus()
                hideKeyboard(activity, focusView)
                onDismissKeyboardListener?.onDismissKeyboard()
            }
        }
    }
}

fun showKeyboard(activity: Activity, view: View?) {
    if (view != null && view.isFocusable && !view.isFocused) {
        view.requestFocus()
    }
    val inputMethodManager = activity
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun hideKeyboard(activity: Activity, view: View) {
    val imm = activity.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

interface OnDismissKeyboardListener {
    fun onDismissKeyboard()
}
