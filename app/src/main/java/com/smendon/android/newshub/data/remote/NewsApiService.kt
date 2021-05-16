package com.smendon.android.newshub.data.remote

import com.smendon.android.newshub.data.remote.models.NewsResponse
import com.smendon.android.newshub.utils.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("$KEY_API_KEY: $NEWSORG_API_KEY")
    @GET("/$API_VERSION/$ENDPOINT_HEADLINES")
    suspend fun getHeadlines(
        @Query(KEY_COUNTRY) country: String = COUNTRY,
        @Query(KEY_PAGE_SIZE) pageSize: Int = PAGE_SIZE
    ): NewsResponse
}