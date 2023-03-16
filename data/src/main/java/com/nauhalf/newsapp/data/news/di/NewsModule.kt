package com.nauhalf.newsapp.data.news.di

import com.nauhalf.newsapp.data.news.api.repository.NewsRepository
import com.nauhalf.newsapp.data.news.implementation.remote.api.NewsApi
import com.nauhalf.newsapp.data.news.implementation.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    @Singleton
    fun provideNewsApi(
        retrofit: Retrofit,
    ): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsApi = newsApi,
            ioDispatcher = Dispatchers.IO,
        )
    }
}
