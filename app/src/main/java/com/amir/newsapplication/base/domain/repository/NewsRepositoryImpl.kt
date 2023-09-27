package com.amir.newsapplication.base.domain.repository

import androidx.lifecycle.LiveData
import com.amir.newsapplication.base.db.NewsDao
import com.amir.newsapplication.base.domain.network.remote.ApiService
import com.amir.newsapplication.base.domain.network.remote.NetworkResource
import com.amir.newsapplication.base.domain.network.remote.SafeApiRequest
import com.amir.newsapplication.base.domain.network.response.Article
import com.amir.newsapplication.base.domain.network.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : NewsRepository, SafeApiRequest() {

    override suspend fun fetchNewsData(): NetworkResource<NewsResponse> {
        val response = withContext(Dispatchers.IO) {
            apiCall { apiService.fetchNews() }
        }
        response.data?.let {
            saveArticlesToDb(it)
        }
        return response
    }

    override suspend fun getNewsArticle(): LiveData<List<Article>> {
        return newsDao.getNewsArticles()
    }


    private suspend fun saveArticlesToDb(response: NewsResponse) {
        withContext(Dispatchers.IO) {
            newsDao.deleteAll()
            response.articles.forEach {
                newsDao.insert(it)
            }
        }
    }
}