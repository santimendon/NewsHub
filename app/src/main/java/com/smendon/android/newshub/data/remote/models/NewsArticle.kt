package com.smendon.android.newshub.data.remote.models

data class NewsArticle(
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val title: String?,
        val url: String?,
        val urlToImage: String?
)