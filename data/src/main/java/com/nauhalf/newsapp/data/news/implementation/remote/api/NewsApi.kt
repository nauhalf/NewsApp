package com.nauhalf.newsapp.data.news.implementation.remote.api

import com.nauhalf.newsapp.data.news.implementation.remote.response.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun fetchTopHeadline(
        @Query("pageSize")
        pageSize: Int = 20,
        @Query("page")
        page: Int = 1,
        @Query("country")
        country: String = "us",
        @Query("category")
        category: String? = null
    ): TopHeadlineResponse
}
