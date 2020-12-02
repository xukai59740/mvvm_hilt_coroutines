package com.eight_centimeter.android.mvvm_coroutines_hilt.utils

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SnackBarUtil {
    fun showErrorSnack(
        activity: Activity,
        msg: String?,
        dismissCallBack: (() -> Unit)? = {}
    ): Snackbar {
        return showSnack(
            activity,
            msg ?: "",
            R.drawable.iv_snack_error,
            dismissCallBack
        )
    }

    fun showOkSnack(
        activity: Activity,
        msg: String?,
        dismissCallBack: (() -> Unit)? = {}
    ): Snackbar {
        return showSnack(
            activity,
            msg ?: "",
            R.drawable.iv_snack_ok,
            dismissCallBack
        )
    }

    private fun showSnack(
        activity: Activity,
        msg: String,
        icon: Int?,
        dismissCallBack: (() -> Unit)? = {}
    ): Snackbar {
        val findViewById =
            activity.window.decorView.findViewById<View>(android.R.id.content)

        val snackBar = Snackbar.make(findViewById, "", Snackbar.LENGTH_SHORT)
            .setDuration(3000)

        val snackView = snackBar.view
        snackView.setPadding(0, 0, 0, 0)

        snackView.background = ContextCompat.getDrawable(activity, R.drawable.bg_snack_rounded)

        val snackBarLayout = snackView as Snackbar.SnackbarLayout
        val addView = LayoutInflater.from(snackView.context).inflate(R.layout.dialog_snack_bg, null)
        val p: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        p.gravity = Gravity.CENTER_VERTICAL
        snackBarLayout.addView(addView, p)

        val snackIcon = addView.findViewById<ImageView>(R.id.ivSnackIcon)
        if (icon != null) {
            snackIcon.visibility = View.VISIBLE
            snackIcon.setImageResource(icon)
        } else {
            snackIcon.visibility = View.GONE
        }

        addView.findViewById<TextView>(R.id.tvSnackContent).text = msg
        addView.findViewById<TextView>(R.id.tvSnackAction).setOnClickListener {
            snackBar.dismiss()
        }

        snackBar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar?>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                if (dismissCallBack != null) {
                    dismissCallBack()
                }
            }
        })

        snackBar.show()
        return snackBar
    }
}
