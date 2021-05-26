package com.smendon.android.newshub.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.smendon.android.newshub.data.local.NewsDatabase
import com.smendon.android.newshub.data.local.entities.NewsArticle
import com.smendon.android.newshub.data.remote.NewsApiService
import com.smendon.android.newshub.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
    private val database: NewsDatabase
) {
    @ExperimentalPagingApi
    fun getNewsStream(): Flow<PagingData<NewsArticle>> {
        val pagingSourceFactory = { database.newsDao().getAllNews() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            remoteMediator = NewsRemoteMediator(
                apiService,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}