package com.nauhalf.newsapp.data.news.api.model

data class GenericPaging<T>(
    val total: Int = 0,
    val data: List<T> = listOf(),
)
