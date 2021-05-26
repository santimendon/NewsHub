package com.smendon.android.newshub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.newshub.databinding.ItemSeperatorBinding

class SeparatorViewHolder(private val binding: ItemSeperatorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(separatorText: String) {
        binding.separatorDescription.text = separatorText.toUpperCase()
    }

    companion object {
        fun create(view: ViewGroup): SeparatorViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemSeperatorBinding.inflate(inflater, view, false)
            return SeparatorViewHolder(binding)
        }
    }
}