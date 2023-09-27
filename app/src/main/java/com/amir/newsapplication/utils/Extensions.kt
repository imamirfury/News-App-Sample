package com.amir.newsapplication.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.amir.newsapplication.base.domain.network.remote.NetworkResource
import com.bumptech.glide.Glide

/**
 * Created by Amir Fury On September 26th 2023
 *
 * Email : fury.amir93@gmail.com
 *
 */

fun Context.string(stringRes: Int): String = resources.getString(stringRes)

fun Context.toast(message: String?) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
}

fun <T> Any.liveData() = MutableLiveData<NetworkResource<T>>()


@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context).load(imageUrl).into(this)
}

