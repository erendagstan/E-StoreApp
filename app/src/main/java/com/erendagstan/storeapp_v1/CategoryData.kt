package com.erendagstan.storeapp_v1

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryData(
    val resId: Int?=null,
    @SerializedName("category")
    val title:String
) : Parcelable
