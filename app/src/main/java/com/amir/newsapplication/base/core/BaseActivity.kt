package com.amir.newsapplication.base.core

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Amir Fury On September 26th 2023
 *
 * Email : fury.amir93@gmail.com
 *
 */

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected val binding: B by lazy { DataBindingUtil.setContentView(this, layoutRes) }

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun getToolbar(binding: B): Toolbar

    protected abstract fun onUiReady(instanceState: Bundle?, binding: B)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(getToolbar(binding))
        onUiReady(savedInstanceState, binding)
    }

    protected fun enableBack() {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun setToolbarTitle(title: String) {
        supportActionBar?.apply {
            setTitle(title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}