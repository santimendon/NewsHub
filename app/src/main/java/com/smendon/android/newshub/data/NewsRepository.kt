package com.smendon.android.newshub.data

import androidx.room.withTransaction
import com.smendon.android.newshub.data.local.NewsDatabase
import com.smendon.android.newshub.data.remote.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
    private val database: NewsDatabase
) {
    private val newsDao = database.newsDao()

    fun getLatestNews() = networkBoundResource(
        queryDb = {
            newsDao.getLatestNews()
        },
        fetchFromRemote = {
            apiService.getHeadlines().articles
        },
        cacheRemoteResult = { newslist ->
            database.withTransaction {
                newsDao.deleteAllNews()
                newsDao.insertLatestNews(newslist)

            }
        },
        shouldFetch = { true }
    )
}