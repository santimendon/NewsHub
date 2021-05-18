package com.smendon.android.newshub.data.remote.models

data class NewsResponse(val articles: List<NewsArticleDto>)

data class NewsArticleDto(
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)