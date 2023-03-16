package com.nauhalf.newsapp.data.news.api.model

data class News(
    val source: Source = Source(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val thumbnail: String = "",
    val publishedAt: String = "",
    val content: String = "",
)
