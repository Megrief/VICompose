package com.vicompose.util

import com.vicompose.core.entity.Image
import com.vicompose.data.network.dto.ImageResponse
import com.vicompose.data.repository.PAGE_SIZE

fun toImage(imageResponse: ImageResponse, page: Int): Image = Image(
    title = imageResponse.title,
    imageUrlFull = imageResponse.imageUrl,
    position = (imageResponse.position - 1) + (page - 1) * PAGE_SIZE,
    link = imageResponse.link,
    imageUrlThumb = imageResponse.thumbnailUrl
)
