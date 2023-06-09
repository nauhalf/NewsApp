package com.nauhalf.newsapp.data.news.implementation.remote.response

import com.google.gson.annotations.SerializedName

data class TopHeadlineResponse(
    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: Int?,

    @SerializedName("articles")
    val articles: List<ArticleResponse>?,
) {
    data class ArticleResponse(
        @SerializedName("source")
        val source: SourceResponse?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("urlToImage")
        val urlToImage: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("content")
        val content: String?,
    )

    data class SourceResponse(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
    )
}
