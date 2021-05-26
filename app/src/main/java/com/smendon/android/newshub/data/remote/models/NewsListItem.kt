package com.smendon.android.newshub.data.remote.models

import com.smendon.android.newshub.data.local.entities.NewsArticle

sealed class NewsListItem {
    data class NewsItem(val newsItem: NewsArticle) : NewsListItem()
    data class SeparatorItem(val letter: String) : NewsListItem()
}