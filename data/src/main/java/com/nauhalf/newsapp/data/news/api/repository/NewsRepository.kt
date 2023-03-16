package com.nauhalf.newsapp.data.news.api.repository

import com.nauhalf.newsapp.core.wrapperresponse.WrapperResponse
import com.nauhalf.newsapp.data.news.api.model.GenericPaging
import com.nauhalf.newsapp.data.news.api.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    /** Fetch Top Headline from News API **/
    fun fetchTopHeadline(
        page: Int,
        pageSize: Int,
    ): Flow<WrapperResponse<GenericPaging<News>>>
}
