package com.smendon.android.newshub.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smendon.android.newshub.data.local.entities.NewsArticle

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsArticle>)

    @Query("SELECT * FROM table_latest_news")
    fun getAllNews(): PagingSource<Int, NewsArticle>

    @Query("DELETE FROM table_latest_news")
    suspend fun deleteAllNews()
}