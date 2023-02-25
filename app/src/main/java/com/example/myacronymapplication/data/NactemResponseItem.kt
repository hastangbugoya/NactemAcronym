package com.example.myacronymapplication.data


import com.google.gson.annotations.SerializedName

data class NactemResponseItem(
    @SerializedName("lfs")
    var lfs: List<Lf>? = listOf(),
    @SerializedName("sf")
    var sf: String? = ""
)