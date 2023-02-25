package com.example.myacronymapplication.data


import com.google.gson.annotations.SerializedName

data class Lf(
    @SerializedName("freq")
    var freq: Int? = 0,
    @SerializedName("lf")
    var lf: String? = "",
    @SerializedName("since")
    var since: Int? = 0,
    @SerializedName("vars")
    var vars: List<Var>? = listOf()
)