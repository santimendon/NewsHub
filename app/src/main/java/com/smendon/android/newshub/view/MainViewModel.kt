package com.smendon.android.newshub.view


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.smendon.android.newshub.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    val latestNews = repository.getLatestNews().asLiveData()
}