package com.nauhalf.newsapp.core.network.api.repository

interface NetworkRepository {
    /** Read News API Base URL **/
    suspend fun readNewsApiBaseUrl(): String

    /** Read News API Key **/
    suspend fun readNewsApiKey(): String
}
