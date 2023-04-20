package com.bugeto.kotlincoroutine.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugeto.kotlincoroutine.R
import com.bugeto.kotlincoroutine.data.User
import com.bugeto.kotlincoroutine.util.loadImage
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(var users: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }


    fun updateData(newUsers:List<User>){
       users.clear()
       users.addAll(newUsers)
           notifyDataSetChanged()
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userAvatar = view.userAvatar
        private val userName = view.userFullName
        private val userEmail = view.userEmail

        fun bind(user: User) {
            userName.text = "${user.first_name}  ${user.last_name}"
            userEmail.text = user.email
            userAvatar.loadImage(user.avatar)
        }
    }

}