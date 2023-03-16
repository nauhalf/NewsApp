package com.nauhalf.newsapp.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nauhalf.newsapp.data.news.api.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {
    val pager = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            NewsPagingSource(
                newsRepository = newsRepository,
            )
        }
    ).flow.cachedIn(viewModelScope)
}
