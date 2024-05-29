package com.vicompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("images")
    val images: List<ImageResponse>
)