package com.amir.newsapplication.di

import com.amir.newsapplication.base.db.NewsDao
import com.amir.newsapplication.base.domain.network.remote.ApiService
import com.amir.newsapplication.base.domain.repository.NewsRepository
import com.amir.newsapplication.base.domain.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepository(apiService: ApiService, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(apiService, newsDao)
    }
}