package com.example.flixster

import com.google.gson.annotations.SerializedName

class BestSellerMovie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("poster_path")
    var bookImageUrl: String? = null

    @SerializedName("overview")
    var description: String? = null
}