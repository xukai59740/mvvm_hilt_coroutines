package com.impulso.app.extension

import android.content.res.Resources

val Float.dp: Float // dp to px
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )
