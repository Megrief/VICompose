package com.vicompose.core.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchUseCase<T : Any> {
    suspend fun search(query: String): Flow<PagingData<T>>
}