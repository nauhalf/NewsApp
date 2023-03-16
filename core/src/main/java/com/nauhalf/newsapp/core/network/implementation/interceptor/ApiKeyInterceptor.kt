package com.nauhalf.newsapp.core.network.implementation.interceptor

import com.nauhalf.newsapp.core.network.api.repository.NetworkRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val networkRepository: NetworkRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val apiKey = runBlocking {
            networkRepository.readNewsApiKey()
        }

        val requestBuilder = request.newBuilder()
            .header("X-Api-Key", apiKey)

        val requestBuild = requestBuilder.build()
        return chain.proceed(requestBuild)
    }

}
