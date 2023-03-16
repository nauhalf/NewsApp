package com.nauhalf.newsapp.view.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nauhalf.newsapp.core.wrapperresponse.HttpError
import com.nauhalf.newsapp.core.wrapperresponse.NoInternetError
import com.nauhalf.newsapp.core.wrapperresponse.TimeOutError
import com.nauhalf.newsapp.core.wrapperresponse.WrapperResponse
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.data.news.api.repository.NewsRepository
import kotlinx.coroutines.flow.first

class NewsPagingSource(
    private val newsRepository: com.nauhalf.newsapp.data.news.api.repository.NewsRepository,
) : PagingSource<Int, com.nauhalf.newsapp.data.news.api.model.News>() {
    override fun getRefreshKey(state: PagingState<Int, com.nauhalf.newsapp.data.news.api.model.News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.nauhalf.newsapp.data.news.api.model.News> {
        val idx = params.key ?: 1
        val result = newsRepository.fetchTopHeadline(
            page = idx,
            pageSize = params.loadSize,
        ).first()

        return when (result) {
            is WrapperResponse.Success -> {
                val data = result.data
                LoadResult.Page(
                    data = data.data,
                    prevKey = if (idx == 1) null else idx - 1,
                    nextKey = if (data.data.size < params.loadSize) null else idx + 1,
                )
            }
            is WrapperResponse.Error -> {
                val msg = when (result) {
                    is HttpError -> {
                        result.message
                    }
                    is NoInternetError, is TimeOutError -> {
                        "Koneksi terputus. Cek sinyal kamu dan coba lagi, ya."
                    }
                    else -> {
                        "Terjadi kesalahan server. Coba beberapa saat lagi, ya."
                    }
                } + "\nSilahkan ketuk untuk mencoba lagi."
                LoadResult.Error(Exception(msg))
            }
            else -> {
                LoadResult.Error(Exception("Terjadi kesalahan server. Coba beberapa saat lagi, ya.\nSilahkan ketuk untuk mencoba lagi."))
            }
        }
    }
}
