package com.erendagstan.storeapp_v1

import com.google.gson.annotations.SerializedName


data class Model(
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("price")
    val price : String,
    @SerializedName("category")
    val category : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("image")
    val image : String
)

