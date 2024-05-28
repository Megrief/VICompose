package com.vicompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String
)