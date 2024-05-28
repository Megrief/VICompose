package com.vicompose.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vicompose.core.entity.Image
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

    override suspend fun search(query: String): Flow<PagingData<Image>> {
        Log.wtf("AAAAA",  "in SearchRepository")
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                Log.wtf("AAAAA", "in PagingSourceFactory")
//                db.imageDao.getImages(query)
                ImagePagingSource(service = service, query = query)
            }
        ).flow
    }

}