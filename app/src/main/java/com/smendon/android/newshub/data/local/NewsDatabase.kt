package com.smendon.android.newshub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smendon.android.newshub.data.local.entities.NewsArticle

@Database(
    entities = arrayOf(NewsArticle::class),
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}