package com.amir.newsapplication.base.domain.network.remote

import com.amir.newsapplication.base.domain.network.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://newsapi.org/v2/"
const val NEWS_API_KEY = "d7f7cbc33af64302a72c6e3eb673a6a5"
const val API_URL = "top-headlines?country=in&category=business&apiKey="

interface ApiService {

    @GET(API_URL.plus(NEWS_API_KEY))
    suspend fun fetchNews(): Response<NewsResponse>
}