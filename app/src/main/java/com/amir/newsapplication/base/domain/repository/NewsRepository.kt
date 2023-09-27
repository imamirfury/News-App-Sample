package com.amir.newsapplication.base.domain.repository

import androidx.lifecycle.LiveData
import com.amir.newsapplication.base.domain.network.remote.NetworkResource
import com.amir.newsapplication.base.domain.network.response.Article
import com.amir.newsapplication.base.domain.network.response.NewsResponse

interface NewsRepository {

    suspend fun fetchNewsData(): NetworkResource<NewsResponse>

    suspend fun getNewsArticle(): LiveData<List<Article>>

}