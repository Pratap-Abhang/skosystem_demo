package com.abhang.skosystem_demo.presentation.user_data_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abhang.skosystem_demo.databinding.UserDataItemBinding
import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.utils.DiffCallback
import com.bumptech.glide.Glide

class UserAdapter(
    private val context: Context,
    private val onClickListener: (id: Int)->Unit
) : ListAdapter<UserData, UserAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: UserDataItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: UserData, context: Context, onClickListener: (id: Int) -> Unit){
            with(userData) {
                binding.nameTxt.text = name
                binding.emailTxt.text = email
                Glide.with(context)
                    .load(avatar)
                    .centerCrop()
                    .into(binding.avatarImg)
                binding.userItem.setOnClickListener {
                    onClickListener(userData.userId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = UserDataItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userData = getItem(position),context= context, onClickListener)
    }



    class DiffCallback: DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.userId==newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.equals(newItem)
        }

    }
}