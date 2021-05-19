package com.smendon.android.newshub.di

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutineDispatcher(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
)