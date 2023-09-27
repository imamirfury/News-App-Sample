package com.amir.newsapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.amir.newsapplication.base.core.BaseActivity
import com.amir.newsapplication.R
import com.amir.newsapplication.adapter.ArticlesAdapter
import com.amir.newsapplication.base.domain.network.remote.Status
import com.amir.newsapplication.base.viewmodel.NewsViewModel
import com.amir.newsapplication.databinding.ActivityHomeBinding
import com.amir.newsapplication.utils.string
import com.amir.newsapplication.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Amir Fury On September 26th 2023
 *
 * Email : fury.amir93@gmail.com
 *
 */

const val ARTICLE = "article"

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val newsViewModel by lazy { ViewModelProvider(this)[NewsViewModel::class.java] }

    private val articlesAdapter by lazy {
        ArticlesAdapter {
            startActivity(Intent(this, ArticleDetailActivity::class.java).apply {
                putExtra(ARTICLE, it)
            })
        }
    }

    override val layoutRes: Int
        get() = R.layout.activity_home

    override fun getToolbar(binding: ActivityHomeBinding): Toolbar = binding.toolbar

    override fun onUiReady(instanceState: Bundle?, binding: ActivityHomeBinding) {
        setToolbarTitle(string(R.string.home))
        with(binding) {
            articleRecycler.adapter = articlesAdapter
        }
        fetchNewsData()
        showNewsData()
    }

    private fun fetchNewsData() {
        newsViewModel.fetchNewsData().observe(this@HomeActivity) {
            when (it?.status) {
                Status.ERROR -> {
                    toast(it.error)
                }
                else -> {
                    toast("Loading Data")
                }
            }
        }
    }

    private fun showNewsData() = CoroutineScope(Dispatchers.Main).launch {
        newsViewModel.getNewsArticles().observe(this@HomeActivity) {
            articlesAdapter.submitList(it.orEmpty())
        }
    }
}