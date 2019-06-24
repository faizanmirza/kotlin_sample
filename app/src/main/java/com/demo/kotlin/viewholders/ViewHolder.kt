package com.demo.kotlin.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.demo.kotlin.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
    val imgUser = view.findViewById<ImageView>(R.id.imgUser)
}