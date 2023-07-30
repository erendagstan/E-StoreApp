package com.erendagstan.storeapp_v1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularData(val title: String,
                       val price : Double,
                       val description : String,
                       val rate : Double,
                       val resId : Int
) : Parcelable
