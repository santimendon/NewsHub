package com.smendon.android.newshub.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.smendon.android.newshub.data.local.NewsDatabase
import com.smendon.android.newshub.data.local.entities.NewsArticle
import com.smendon.android.newshub.data.local.entities.RemoteKey
import com.smendon.android.newshub.data.remote.NewsApiService
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class NewsRemoteMediator(
    private val api: NewsApiService,
    private val database: NewsDatabase
) : RemoteMediator<Int, NewsArticle>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsArticle>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = api.getHeadlines(page = page, pageSize = state.config.pageSize).articles

            val isEndOfList = response.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.newsDao().deleteAllNews()
                    database.remoteKeysDao().deleteAllKeys()
                }

                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.map {
                    RemoteKey(it.url, prevKey = prevKey, nextKey = nextKey)
                }

                database.remoteKeysDao().insertAllKeys(keys)
                database.newsDao().insertNews(response)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, NewsArticle>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, NewsArticle>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                database.remoteKeysDao().remoteKeysNewsId(url)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, NewsArticle>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { article -> database.remoteKeysDao().remoteKeysNewsId(article.url) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, NewsArticle>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { article -> database.remoteKeysDao().remoteKeysNewsId(article.url) }
    }
}