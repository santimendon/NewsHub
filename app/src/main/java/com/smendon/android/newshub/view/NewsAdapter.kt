package com.smendon.android.newshub.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smendon.android.newshub.R
import com.smendon.android.newshub.data.local.entities.NewsArticle
import kotlinx.android.synthetic.main.item_news_article.view.*
import java.text.SimpleDateFormat

class NewsArticleAdapter : ListAdapter<NewsArticle, NewsArticleAdapter.ViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: NewsArticle) {
            itemView.txt_title.text = item.title
            itemView.txt_date.text = "Published at ${formatDate(item.publishedAt)}"
            Glide.with(itemView.image.context).load(item.urlToImage).into(itemView.image)
        }

        fun formatDate(responseDate: String?): String {
            responseDate?.let {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val myFormat = SimpleDateFormat("HH:mm, dd MMM")
                return myFormat.format(parser.parse(responseDate))
            }
            return ""
        }
    }
}

class DiffUtil : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem == newItem
    }
}