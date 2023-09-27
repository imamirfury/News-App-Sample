package com.amir.newsapplication.base.domain.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    @SerializedName("articles") val articles: ArrayList<Article>,
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int
)

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val urlToImage: String?
) : Serializable

data class Source(
    @SerializedName("id") val id: Any?, @SerializedName("name") val name: String
)