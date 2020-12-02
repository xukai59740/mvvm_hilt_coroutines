package com.eight_centimeter.android.mvvm_coroutines_hilt.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.eight_centimeter.android.mvvm_coroutines_hilt.R

fun showConfirmDialog(
    activity: Activity?,
    title: String?,
    subTitle: String? = null,
    onDialogActionListener: OnDialogActionListener? = null,
    disableNegative: Boolean = false,
    cancelable: Boolean = true,
    autoDismiss: Boolean = true,
    positiveText: String? = null,
    negativeText: String? = null,
    positiveBackground: Drawable? = null
) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    val viewGroup: ViewGroup? = activity?.findViewById(android.R.id.content)
    val dialogView: View =
        LayoutInflater.from(activity).inflate(R.layout.dialog_comfirm, viewGroup, false)
    dialogView.findViewById<TextView>(R.id.tvTitle)?.apply {
        text = title
    }
    dialogView.findViewById<TextView>(R.id.tvSubTitle)?.apply {
        isVisible = subTitle != null
        text = subTitle ?: ""
    }

    builder.setView(dialogView)
    val alertDialog: AlertDialog = builder.create()
    dialogView.findViewById<TextView>(R.id.tvCancel).apply {
        if (negativeText != null) {
            text = negativeText
        }

        setOnClickListener {
            if (autoDismiss) alertDialog.dismiss()
            onDialogActionListener?.onNegativeBtnClick(alertDialog)
        }
    }

    dialogView.findViewById<TextView>(R.id.tvYes).apply {
        if (positiveBackground != null) {
            this.background = positiveBackground
        }
        if (positiveText != null) {
            text = positiveText
        }
        setOnClickListener {
            if (autoDismiss) alertDialog.dismiss()
            onDialogActionListener?.onPositiveBtnClick(alertDialog)
        }
    }
    if (disableNegative) {
        dialogView.findViewById<TextView>(R.id.tvCancel).isVisible = false
    }
    alertDialog.setCanceledOnTouchOutside(cancelable)
    alertDialog.setCancelable(cancelable)
    alertDialog.show()
}

interface OnDialogActionListener {
    fun onPositiveBtnClick(alertDialog: AlertDialog)
    fun onNegativeBtnClick(alertDialog: AlertDialog)
}
