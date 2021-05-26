package com.smendon.android.newshub.utils

import com.smendon.android.newshub.BuildConfig

/*
 * @HOST https://newsapi.org/
 * @API_VERSION v1/v2
 * Base URL for Retrofit object
 */
const val HOST = "https://newsapi.org"
const val API_VERSION = "v2" //To be migrated to Firebase

const val BASE_URL = HOST

const val ENDPOINT_HEADLINES = "top-headlines"

/*
 * API Query's
 */
const val KEY_COUNTRY = "country"
const val KEY_PAGE = "page"
const val KEY_PAGE_SIZE = "pageSize"

/*
 * Newsorg API Key-Value
 */
const val KEY_API_KEY = "X-Api-Key"
const val NEWSORG_API_KEY = BuildConfig.NEWSORG_API_KEY

/*
* Defaukt API Query values
*/
const val COUNTRY = "in" //Primary country for headlines
const val DEFAULT_PAGE = 1 //Default page
const val PAGE_SIZE = 1 //Headlines count per hit

/**
 * Database name
 */
const val DATABASE_NAME = "Newshub.db"