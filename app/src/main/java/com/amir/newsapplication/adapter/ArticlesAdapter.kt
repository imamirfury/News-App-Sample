package com.amir.newsapplication.adapter

import androidx.recyclerview.widget.DiffUtil
import com.amir.newsapplication.R
import com.amir.newsapplication.base.core.BaseListAdapter
import com.amir.newsapplication.base.domain.network.response.Article

class ArticlesAdapter(private val onClick: (Article) -> Unit) :
    BaseListAdapter<Article>(diffItemCallback = diffCallback, onClick) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_news
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id && oldItem.url == newItem.url && oldItem.author == newItem.author && oldItem.title == newItem.title && oldItem.urlToImage == newItem.urlToImage
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id && oldItem.url == newItem.url && oldItem.author == newItem.author && oldItem.title == newItem.title && oldItem.urlToImage == newItem.urlToImage
            }
        }
    }
}