package com.nauhalf.newsapp.core.network.module

import android.content.Context
import com.nauhalf.newsapp.core.network.api.repository.NetworkRepository
import com.nauhalf.newsapp.core.network.implementation.interceptor.ApiKeyInterceptor
import com.nauhalf.newsapp.core.network.implementation.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkRepository(): NetworkRepository {
        return NetworkRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideNetworkProvider(
        @ApplicationContext context: Context,
        networkRepository: NetworkRepository,
    ): NetworkProvider {
        val url = runBlocking {
            networkRepository.readNewsApiBaseUrl()
        }
        return NetworkProvider(
            context = context,
            url = url,
            interceptors = listOf(
                ApiKeyInterceptor(networkRepository)
            )
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(networkProvider: NetworkProvider): Retrofit {
        return networkProvider.createRetrofit()
    }


}
