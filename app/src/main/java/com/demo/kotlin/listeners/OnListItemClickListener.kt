package com.demo.kotlin.listeners

import com.demo.kotlin.entities.User

interface OnListItemClickListener {
    fun onListItemClick(user: User)
}