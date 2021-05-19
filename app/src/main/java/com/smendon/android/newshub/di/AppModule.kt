package com.smendon.android.newshub.di

import android.content.Context
import androidx.room.Room
import com.smendon.android.newshub.data.local.NewsDatabase
import com.smendon.android.newshub.data.remote.NewsApiService
import com.smendon.android.newshub.utils.BASE_URL
import com.smendon.android.newshub.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchers() = AppCoroutineDispatcher(
        main = Dispatchers.Main,
        io = Dispatchers.IO,
        computation = Dispatchers.Default
    )

    @Provides
    @Main
    fun provideMainDispatcher(dispatcher: AppCoroutineDispatcher) = dispatcher.main

    @Provides
    @Io
    fun provideIoDispatcher(dispatcher: AppCoroutineDispatcher) = dispatcher.io

    @Provides
    @Common
    fun provideComputationDispatcher(dispatcher: AppCoroutineDispatcher) = dispatcher.computation

    @Provides
    @Singleton
    fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit) =
        retrofit.create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        DATABASE_NAME
    ).build()
}