package com.nauhalf.newsapp.data.news.implementation.repository

import com.nauhalf.newsapp.core.wrapperresponse.WrapperResponse
import com.nauhalf.newsapp.core.wrapperresponse.toError
import com.nauhalf.newsapp.data.news.api.model.GenericPaging
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.data.news.api.repository.NewsRepository
import com.nauhalf.newsapp.data.news.implementation.mapper.toGenericPagingNews
import com.nauhalf.newsapp.data.news.implementation.remote.api.NewsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val ioDispatcher: CoroutineDispatcher,
) : NewsRepository {
    override fun fetchTopHeadline(
        page: Int,
        pageSize: Int,
        category: String,
    ): Flow<WrapperResponse<GenericPaging<News>>> = flow {
        try {
            // call API fetchTopHeadline
            val data = newsApi.fetchTopHeadline(
                page = page,
                pageSize = pageSize,
                category = category
            )
            // if status is not ok but http code 2XX, emit an general error
            // if articles is null, emit an empty response
            // else set the emit value to Success with mapped response to Generic Paging<News> data class
            when {
                data.status != "ok" -> emit(WrapperResponse.Error("General Error"))
                data.articles == null -> emit(WrapperResponse.Empty)
                else -> emit(WrapperResponse.Success(data = data.toGenericPagingNews()))
            }
        } catch (e: Exception) {
            // when it throw an error, catch the exception and emit mapped Error from the exception
            emit(e.toError())
        }
        // run the process on Dispatcher.IO
    }.flowOn(ioDispatcher)
}
