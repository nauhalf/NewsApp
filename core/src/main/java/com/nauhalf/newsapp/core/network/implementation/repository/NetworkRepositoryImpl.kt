package com.nauhalf.newsapp.core.network.implementation.repository

import com.nauhalf.newsapp.core.BuildConfig
import com.nauhalf.newsapp.core.network.api.repository.NetworkRepository

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun readNewsApiBaseUrl(): String {
        return BuildConfig.NewsAppBaseUrl
    }

    override suspend fun readNewsApiKey(): String {
        return BuildConfig.NewsApiKey
    }
}
