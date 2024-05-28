package com.vicompose.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vicompose.core.entity.Image
import com.vicompose.data.network.service.SerperApiService
import com.vicompose.data.util.toImage
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(
    private val service: SerperApiService,
    private val query: String
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> = try {
        val currentPage = params.key?: 1
        val result = service.search(
            page = currentPage,
            query = query
        )

        LoadResult.Page(
            data = result.images.map { toImage(it, currentPage - 1) },
            prevKey = if (currentPage > 1) currentPage - 1 else null,
            nextKey = if (result.images.isNotEmpty()) currentPage + 1 else null
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException){
        LoadResult.Error(e)
    }

}