package com.amir.newsapplication.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.amir.newsapplication.R
import com.amir.newsapplication.base.core.BaseActivity
import com.amir.newsapplication.base.domain.network.response.Article
import com.amir.newsapplication.databinding.ActivityArticleDetailBinding
import com.amir.newsapplication.utils.string

class ArticleDetailActivity : BaseActivity<ActivityArticleDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_article_detail

    override fun getToolbar(binding: ActivityArticleDetailBinding): Toolbar = binding.toolbar

    override fun onUiReady(instanceState: Bundle?, binding: ActivityArticleDetailBinding) {
        enableBack()
        setToolbarTitle(string(R.string.articleDetail))
        val article = intent?.getSerializableExtra(ARTICLE) as Article?
        article?.let {
            binding.model = it
        }
    }

}