package com.example.practice.data.adminpanel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.data.files.Users
import com.example.practice.databinding.AdminListUsersBinding

class UserAdapter (private val userList: List<Users>,private val onDeleteClick: (Users) -> Unit): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(private val binding: AdminListUsersBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: Users){
            binding.tvUserName.text = user.name

            binding.btnDelete.setOnClickListener {
                onDeleteClick(user)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = AdminListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }


    override fun getItemCount(): Int {
        return userList.size
    }


}