package com.amir.newsapplication.base.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amir.newsapplication.base.domain.network.response.Article

@Database(entities = [Article::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object{
        operator fun invoke(context: Context): NewsDatabase {
            return Room.databaseBuilder(context, NewsDatabase::class.java, "news.db").build()
        }
    }
}