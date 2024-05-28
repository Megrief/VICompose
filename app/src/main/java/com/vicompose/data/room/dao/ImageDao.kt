package com.vicompose.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vicompose.data.room.dto.ImageDto

@Dao
interface ImageDao {

    @Query("SELECT * FROM image_table WHERE `query` = :query ORDER BY page & position")
    fun getImages(query: String): PagingSource<Int, ImageDto>

    @Insert(entity = ImageDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeImages(images: List<ImageDto>)

    @Query("DELETE FROM image_table")
    suspend fun clearImages()

}