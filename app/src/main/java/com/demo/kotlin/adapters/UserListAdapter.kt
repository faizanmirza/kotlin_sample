package com.demo.kotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.demo.kotlin.R
import com.demo.kotlin.entities.User
import com.demo.kotlin.listeners.OnListItemClickListener
import com.demo.kotlin.viewholders.ViewHolder

class UserListAdapter(
    private val items: ArrayList<User>,
    private val context: Context,
    private val onListItemClickListener: OnListItemClickListener?
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var user = items[position]

        holder.tvName?.text = user.getName()
        holder.tvEmail?.text = user.email
        Glide.with(context).load(user.profilePic).into(holder.imgUser)

        holder.itemView.setOnClickListener {
            onListItemClickListener?.onListItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}