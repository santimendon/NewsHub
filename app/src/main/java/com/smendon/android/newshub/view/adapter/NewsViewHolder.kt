package com.smendon.android.newshub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smendon.android.newshub.data.local.entities.NewsArticle
import com.smendon.android.newshub.databinding.ItemNewsArticleBinding
import java.text.SimpleDateFormat

class NewsViewHolder(private val binding: ItemNewsArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NewsArticle?) {
        binding.txtTitle.text = item?.title
        binding.txtDate.text = "Published at ${formatDate(item?.publishedAt)}"
        Glide.with(binding.image.context).load(item?.urlToImage).into(binding.image)
    }

    companion object {
        fun create(view: ViewGroup): NewsViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemNewsArticleBinding.inflate(inflater, view, false)
            return NewsViewHolder(binding)
        }
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