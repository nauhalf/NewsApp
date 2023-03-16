package com.nauhalf.newsapp.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.databinding.ItemNewsBinding
import com.nauhalf.newsapp.util.toDate
import com.nauhalf.newsapp.util.toFormat

class NewsAdapter : PagingDataAdapter<com.nauhalf.newsapp.data.news.api.model.News, NewsAdapter.NewsViewHolder>(
    object : DiffUtil.ItemCallback<com.nauhalf.newsapp.data.news.api.model.News>() {
        override fun areItemsTheSame(oldItem: com.nauhalf.newsapp.data.news.api.model.News, newItem: com.nauhalf.newsapp.data.news.api.model.News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: com.nauhalf.newsapp.data.news.api.model.News, newItem: com.nauhalf.newsapp.data.news.api.model.News): Boolean {
            return oldItem == newItem
        }

    }
) {
    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: com.nauhalf.newsapp.data.news.api.model.News) {
            with(binding) {
                tvTitle.text = item.title
                tvAuthor.text = item.author
                Glide.with(ivImg)
                    .load(item.thumbnail)
                    .centerCrop()
                    .into(ivImg)
                tvPublishedAt.text = item.publishedAt.toDate().toFormat()
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
