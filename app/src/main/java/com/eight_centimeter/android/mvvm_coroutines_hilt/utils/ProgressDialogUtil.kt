package com.eight_centimeter.android.mvvm_coroutines_hilt.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.core.content.ContextCompat
import com.eight_centimeter.android.mvvm_coroutines_hilt.R

object ProgressDialogUtil {
    private var dialog: Dialog? = null
    fun showProgressDialog(context: Context) {
        if (dialog == null) {
            dialog = Dialog(context, R.style.CustomProgress)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.dialog_progress)
            dialog?.window?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        android.R.color.transparent
                    )
                )
            )
            dialog?.setCancelable(false)
        }
        dialog?.show()
    }

    fun hideProgressDialog() {
        if (dialog != null) {
            if (dialog!!.isShowing) {
                dialog?.dismiss()
            }
            dialog = null
        }
    }
}
