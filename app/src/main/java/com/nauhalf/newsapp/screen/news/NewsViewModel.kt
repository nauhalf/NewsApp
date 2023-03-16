package com.nauhalf.newsapp.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nauhalf.newsapp.data.news.api.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {


    private val _categories = MutableStateFlow(
        listOf(
            Category(
                label = "General",
                value = "general",
            ),

            Category(
                label = "Business",
                value = "business",
            ),

            Category(
                label = "Entertainment",
                value = "entertainment",
            ),

            Category(
                label = "Health",
                value = "health",
            ),

            Category(
                label = "Science",
                value = "science",
            ),

            Category(
                label = "Sports",
                value = "sports",
            ),

            Category(
                label = "Technology",
                value = "technology",
            ),
        )
    )


    val categories = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow(_categories.value.first())
    val selectedCategory = _selectedCategory.asStateFlow()

    val newsPaging = selectedCategory.flatMapLatest {
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsRepository = newsRepository,
                    category = it
                )
            }
        ).flow
    }.cachedIn(viewModelScope)

    fun setSelectedCategory(value: Category) {
        _selectedCategory.value = value
    }
}
