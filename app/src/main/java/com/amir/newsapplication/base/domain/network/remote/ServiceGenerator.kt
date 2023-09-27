package com.amir.newsapplication.base.domain.network.remote

import com.amir.newsapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val timeOut = 60L

object ServiceGenerator {

    operator fun invoke(
        connectivityInterceptor: ConnectivityInterceptor
    ): ApiService {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(timeout = timeOut, TimeUnit.SECONDS)
            readTimeout(timeout = timeOut, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(connectivityInterceptor)
        }.build()

        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}