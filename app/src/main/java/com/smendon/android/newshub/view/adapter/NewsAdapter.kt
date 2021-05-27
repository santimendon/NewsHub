package com.smendon.android.newshub.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.newshub.data.remote.models.NewsListItem

class NewsAdapter : PagingDataAdapter<NewsListItem, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NewsListItem.NewsItem -> ViewType.NEWS.ordinal
            is NewsListItem.SeparatorItem -> ViewType.SEPARATOR.ordinal
            null -> throw UnsupportedOperationException("Unexpected View")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.NEWS.ordinal) {
            NewsViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it) {
                is NewsListItem.NewsItem -> (holder as? NewsViewHolder)?.bind(it.newsItem)
                is NewsListItem.SeparatorItem -> (holder as? SeparatorViewHolder)?.bind(it.letter)
            }
        }
    }

    companion object {
        private enum class ViewType {
            NEWS, SEPARATOR
        }

        private val COMPARATOR = object : DiffUtil.ItemCallback<NewsListItem>() {
            override fun areItemsTheSame(oldItem: NewsListItem, newItem: NewsListItem): Boolean {
                return compareCat(oldItem, newItem) ||
                        compareSeparator(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: NewsListItem, newItem: NewsListItem): Boolean =
                oldItem == newItem
        }

        private fun compareSeparator(
            oldItem: NewsListItem,
            newItem: NewsListItem
        ) = (oldItem is NewsListItem.SeparatorItem && newItem is NewsListItem.SeparatorItem &&
                oldItem.letter == newItem.letter)

        private fun compareCat(
            oldItem: NewsListItem,
            newItem: NewsListItem
        ) = (oldItem is NewsListItem.NewsItem && newItem is NewsListItem.NewsItem &&
                oldItem.newsItem.title == newItem.newsItem.title)
    }
}