package com.example.flixster

import com.google.gson.annotations.SerializedName

class BestSellerMovie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("book_image")
    var bookImageUrl: String? = null

    @SerializedName("description")
    var description: String? = null
}