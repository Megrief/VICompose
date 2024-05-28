package com.vicompose.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vicompose.data.network.service.SerperApiService
import com.vicompose.data.room.db.VeryInterestingDb
import com.vicompose.data.room.dto.ImageDto
import com.vicompose.data.room.dto.RemoteKey
import com.vicompose.data.util.toImageDto
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val STARTING_PAGE = 1
@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val service: SerperApiService,
    private val db: VeryInterestingDb,
    private val query: String
) : RemoteMediator<Int, ImageDto>() {

    init {
        Log.wtf("AAAAA", "in remoteMediator init")
    }

    override suspend fun initialize(): InitializeAction {
        val timeout = TimeUnit.MINUTES.convert(30, TimeUnit.MILLISECONDS)
        val lastCreated = db.remoteKeyDao.getLastRemoteKeyCreated() ?: 0
        return if (System.currentTimeMillis() - lastCreated > timeout) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDto>
    ): MediatorResult {

        Log.wtf("AAAAA", "in remoteMediator load")
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.next?.minus(1) ?: STARTING_PAGE

            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prev
                    ?: return MediatorResult.Success(endOfPaginationReached = false)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.next
                    ?: return MediatorResult.Success(endOfPaginationReached = false)
            }
        }

        return try {
            val apiResponse = service.search(query, page)

            Log.wtf("AAAAA", "in remoteMediator results")
            val images = apiResponse.images
            val endOfPaginationReached = images.isEmpty()

            with(db) {
                withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        remoteKeyDao.clearRemoteKeys()
                        imageDao.clearImages()
                    }

                    val prevKey = if (page == STARTING_PAGE) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = images.map {
                        RemoteKey(
                            id = it.imageUrl,
                            prev = prevKey,
                            next = nextKey
                        )
                    }
                    remoteKeyDao.insert(keys)
                    imageDao.storeImages(images.map { toImageDto(it, query, page) })
                }
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ImageDto>): RemoteKey? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { image ->
                db.remoteKeyDao.getRemoteKey(image.imageUrl)
            }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ImageDto>): RemoteKey? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { image ->
                db.remoteKeyDao.getRemoteKey(image.imageUrl)
            }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ImageDto>): RemoteKey? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.imageUrl?.let { imageUrl ->
                db.remoteKeyDao.getRemoteKey(imageUrl)
            }
        }
}