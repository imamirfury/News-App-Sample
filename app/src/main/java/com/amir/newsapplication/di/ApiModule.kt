package com.amir.newsapplication.di

import android.content.Context
import com.amir.newsapplication.base.domain.network.remote.ApiService
import com.amir.newsapplication.base.domain.network.remote.ConnectivityInterceptor
import com.amir.newsapplication.base.domain.network.remote.ConnectivityInterceptorImpl
import com.amir.newsapplication.base.domain.network.remote.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideNetworkInterceptor(@ApplicationContext  context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(context)
    }

    @Provides
    fun provideApiService(
        connectivityInterceptor: ConnectivityInterceptor
    ): ApiService {
        return ServiceGenerator.invoke(connectivityInterceptor)
    }
}