package com.vicompose.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val link: String,
    val position: Int,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,
    val page: Int,
    val title: String,
    val query: String
)
