package com.erendagstan.storeapp_v1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Products(
    val title: String,
    val price: Double,
    val description: String,
    val rate: Double,
    val resId: Int,
    val category : @RawValue CategoryData?=null
) : Parcelable
