package com.vicompose.data.network.dto

data class ApiResponse(
    val searchParameters: SearchParameters,
    val images: List<ImageResponse>
)