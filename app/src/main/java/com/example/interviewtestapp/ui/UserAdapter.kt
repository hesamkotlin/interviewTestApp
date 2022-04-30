package com.example.interviewtestapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewtestapp.R
import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.databinding.ItemLinearCardBinding as ItemLinearCardBinding1


class GifAdapter: androidx.recyclerview.widget.ListAdapter<User, GifAdapter.UserViewHolder>(
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

) {

    inner class UserViewHolder(
        private val binding: ItemLinearCardBinding1
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.model = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemLinearCardBinding1>(
            LayoutInflater.from(parent.context),
            R.layout.item_linear_card,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }

}
