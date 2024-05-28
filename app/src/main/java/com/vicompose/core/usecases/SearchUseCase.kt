package com.vicompose.core.usecases

import androidx.paging.PagingData
import com.vicompose.core.entity.Image
import com.vicompose.data.room.dto.ImageDto
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun search(query: String): Flow<PagingData<ImageDto>>
}