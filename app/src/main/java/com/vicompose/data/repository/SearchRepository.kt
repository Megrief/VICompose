package com.vicompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vicompose.core.entity.Image
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.network.service.SerperApiService
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 10

class SearchRepository(private val service: SerperApiService) : SearchUseCase<Image> {

    override suspend fun search(query: String): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImagePagingSource(service = service, query = query)
            }
        ).flow
    }

}