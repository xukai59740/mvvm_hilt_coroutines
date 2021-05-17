package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployerResponse(
    val employerId: String,
    val businessName: String?
) : Parcelable