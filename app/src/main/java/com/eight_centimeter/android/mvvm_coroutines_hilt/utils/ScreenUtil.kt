package com.eight_centimeter.android.mvvm_coroutines_hilt.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager

fun getScreenWidth(activity: Activity): Int {
    val outMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getRealMetrics(outMetrics)
    return outMetrics.widthPixels
}

fun getScreenHeight(activity: Activity): Int {
    val outMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getRealMetrics(outMetrics)
    return outMetrics.heightPixels
}

fun getWindowHeight(activity: Activity): Int {
    val outMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}

fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun getNavigationBarHeight(var0: Context): Int {
    val var1: Boolean = ViewConfiguration.get(var0).hasPermanentMenuKey()
    var var2: Int
    return if (var0.resources.getIdentifier("navigation_bar_height", "dimen", "android").also {
        var2 = it
    } > 0 && !var1
    ) var0.resources.getDimensionPixelSize(var2) else 0
}

fun isShowNavBar(context: Context?): Boolean {
    if (null == context) {
        return false
    }
    val outRect1 = Rect()
    try {
        (context as Activity).window.decorView.getWindowVisibleDisplayFrame(outRect1)
    } catch (e: ClassCastException) {
        e.printStackTrace()
        return false
    }
    val activityHeight = outRect1.height()
    val remainHeight =
        getRealHeight(context) - getStatusBarHeight(context) - getNavigationBarHeight(context)
    return activityHeight == remainHeight
}

fun getRealHeight(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getRealSize(point)
    return point.y
}

fun getAxisYPositionOfViewOnScreen(targetView: View): Int {
    val locationTarget = IntArray(2)
    targetView.getLocationOnScreen(locationTarget)
    return locationTarget[1]
}

fun getAxisXPositionOfViewOnScreen(targetView: View): Int {
    val locationTarget = IntArray(2)
    targetView.getLocationOnScreen(locationTarget)
    return locationTarget[0]
}
