package com.amir.newsapplication.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.newsapplication.base.domain.network.remote.NetworkResource
import com.amir.newsapplication.base.domain.repository.NewsRepository
import com.amir.newsapplication.base.domain.network.response.Article
import com.amir.newsapplication.base.domain.network.response.NewsResponse
import com.amir.newsapplication.utils.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    fun fetchNewsData(): LiveData<NetworkResource<NewsResponse>> {
        return liveData<NewsResponse>().apply {
            viewModelScope.launch { postValue(repository.fetchNewsData()) }
        }
    }

    suspend fun getNewsArticles(): LiveData<List<Article>> = repository.getNewsArticle()
}