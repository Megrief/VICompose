package com.vicompose.core.entity

data class Image(
    val imageUrlFull: String,
    val imageUrlThumb: String,
    val link: String, // url of the image source
    val position: Int,
    val title: String
)