package com.amir.newsapplication.base.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amir.newsapplication.base.domain.network.response.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM Article")
    fun getNewsArticles(): LiveData<List<Article>>

    @Query("DELETE FROM Article")
    fun deleteAll()

}