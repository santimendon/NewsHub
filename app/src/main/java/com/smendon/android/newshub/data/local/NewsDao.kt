package com.smendon.android.newshub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smendon.android.newshub.data.local.entities.NewsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestNews(latestNews: List<NewsArticle>)

    @Query("SELECT * FROM table_latest_news")
    fun getLatestNews(): Flow<List<NewsArticle>>

    @Query("DELETE FROM table_latest_news")
    suspend fun deleteAllNews()
}