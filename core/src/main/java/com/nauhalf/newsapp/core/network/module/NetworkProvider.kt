package com.nauhalf.newsapp.core.network.module

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.nauhalf.newsapp.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkProvider(
    private val context: Context,
    private val url: String,
    private val interceptors: List<Interceptor> = listOf(),
) {

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)

        if (BuildConfig.DEBUG) {
            httpBuilder.addInterceptor(getHttpLoggingInterceptor())
            httpBuilder.addInterceptor(getChuckerInterceptor())
        }

        interceptors.forEach {
            httpBuilder.addInterceptor(it)
        }

        return httpBuilder.build()
    }


    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Log.d("Interceptor", message)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    private fun getChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}
