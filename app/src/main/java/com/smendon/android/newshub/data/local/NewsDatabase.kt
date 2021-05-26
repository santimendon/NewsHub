package com.smendon.android.newshub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smendon.android.newshub.data.local.dao.NewsDao
import com.smendon.android.newshub.data.local.dao.RemoteKeyDao
import com.smendon.android.newshub.data.local.entities.NewsArticle
import com.smendon.android.newshub.data.local.entities.RemoteKey

@Database(entities = [NewsArticle::class, RemoteKey::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun remoteKeysDao(): RemoteKeyDao
}