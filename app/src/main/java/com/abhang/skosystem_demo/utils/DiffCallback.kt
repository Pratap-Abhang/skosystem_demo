package com.abhang.skosystem_demo.utils

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return false
    }

}