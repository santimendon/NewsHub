package com.smendon.android.newshub.view


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.smendon.android.newshub.data.NewsRepository
import com.smendon.android.newshub.data.remote.models.NewsListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    val newsStream: Flow<PagingData<NewsListItem>> = repository.getNewsStream()
        .map { pagingData -> pagingData.map { NewsListItem.NewsItem(it) } }
        .map {
            it.insertSeparators<NewsListItem.NewsItem, NewsListItem> { before, after ->
                //Currently not adding any seperators
                return@insertSeparators null
            }
        }
        .cachedIn(viewModelScope)
}