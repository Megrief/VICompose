package com.vicompose.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vicompose.core.entity.Image
import com.vicompose.data.network.service.SerperApiService
import com.vicompose.util.toImage
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(
    private val service: SerperApiService,
    private val query: String
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(STARTING_PAGE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(STARTING_PAGE)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> = try {
        val currentPage = params.key?: STARTING_PAGE
        val result = service.search(
            page = currentPage,
            query = query
        )

        LoadResult.Page(
            data = result.images.map { toImage(it, currentPage) },
            prevKey = if (currentPage > STARTING_PAGE) currentPage - STARTING_PAGE else null,
            nextKey = if (result.images.isNotEmpty()) currentPage + STARTING_PAGE else null
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException){
        LoadResult.Error(e)
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}