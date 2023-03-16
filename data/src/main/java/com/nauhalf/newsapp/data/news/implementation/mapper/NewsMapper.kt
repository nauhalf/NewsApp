package com.nauhalf.newsapp.data.news.implementation.mapper

import com.nauhalf.newsapp.data.news.api.model.GenericPaging
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.data.news.api.model.Source
import com.nauhalf.newsapp.data.news.implementation.remote.response.TopHeadlineResponse

fun TopHeadlineResponse.toGenericPagingNews(): GenericPaging<News> {
    return GenericPaging(
        total = totalResults ?: 0,
        data = articles?.map { it.toNews() } ?: listOf(),
    )
}

fun TopHeadlineResponse.ArticleResponse.toNews(): News {
    return News(
        source = source?.toSource() ?: Source(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        thumbnail = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty(),
    )
}

fun TopHeadlineResponse.SourceResponse.toSource(): Source {
    return Source(
        id = id.orEmpty(),
        name = name.orEmpty(),
    )
}
