package com.amir.newsapplication.base.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amir.newsapplication.BR

abstract class BaseListAdapter<T>(
    diffItemCallback: DiffUtil.ItemCallback<T>,
    private val onClick: (T) -> Unit
) :
    ListAdapter<T, BaseListAdapter<T>.BaseViewHolder>(diffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        onBind(holder.binding, position, item)
    }

    protected open fun onBind(binding: ViewDataBinding, position: Int, item: T) {}

    inner class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}