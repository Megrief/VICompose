package com.vicompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vicompose.core.usecases.SearchUseCase
import com.vicompose.data.network.service.SerperApiService
import com.vicompose.data.room.db.VeryInterestingDb
import com.vicompose.data.room.dto.ImageDto
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 10

class SearchRepository(
    private val service: SerperApiService,
    private val db: VeryInterestingDb,
) : SearchUseCase {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun search(query: String): Flow<PagingData<ImageDto>> = Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            remoteMediator = SearchRemoteMediator(service  = service, db = db, query  = query),
            pagingSourceFactory = {
//                ImagePagingSource(service = service, query = query)
                db.imageDao.getImages(query)
            }
        ).flow


}