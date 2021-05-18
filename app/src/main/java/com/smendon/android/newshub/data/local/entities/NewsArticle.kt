package com.smendon.android.newshub.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_latest_news")
data class NewsArticle(
    @PrimaryKey
    val url: String,
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?,
    val isBookmarked: Boolean,
    val updatedAt: Long = System.currentTimeMillis()
)