package com.demo.kotlin.services

import retrofit2.Call
import retrofit2.http.GET


interface UserService {

    @GET("users?page=1&per_page=10")
    fun getUsers(): Call<UserResponse>
}