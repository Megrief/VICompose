package com.vicompose.util

import com.vicompose.core.entity.Image
import com.vicompose.data.network.dto.ImageResponse
import com.vicompose.data.repository.PAGE_SIZE
import com.vicompose.data.room.dto.ImageDto

fun toImage(imageResponse: ImageResponse, page: Int): Image = Image(
    title = imageResponse.title,
    imageUrlFull = imageResponse.imageUrl,
    position = (imageResponse.position - 1) + (page - 1) * PAGE_SIZE,
    link = imageResponse.link,
    imageUrlThumb = imageResponse.thumbnailUrl
)

fun toImageDto(response: ImageResponse, query: String, page: Int): ImageDto = ImageDto(
    imageUrl = response.imageUrl,
    thumbnailUrl = response.thumbnailUrl,
    title = response.title,
    link = response.link,
    position = response.position,
    query = query,
    page = page
)

fun ImageDto.toImage(): Image  = Image(
    title = this.title,
    imageUrlFull = this.imageUrl,
    position = this.position,
    link = this.link,
    imageUrlThumb = this.thumbnailUrl
)

