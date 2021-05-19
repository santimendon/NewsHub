package com.smendon.android.newshub.data.remote.models

import com.smendon.android.newshub.data.local.entities.NewsArticle

data class NewsResponse(val articles: List<NewsArticle>)