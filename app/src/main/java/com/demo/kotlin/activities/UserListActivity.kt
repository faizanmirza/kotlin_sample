package com.demo.kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.demo.kotlin.R
import com.demo.kotlin.adapters.UserListAdapter
import com.demo.kotlin.entities.User
import com.demo.kotlin.listeners.OnListItemClickListener
import com.demo.kotlin.services.RetrofitManager
import com.demo.kotlin.services.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity(), OnListItemClickListener, Callback<UserResponse> {

    private var usersList: ArrayList<User> = ArrayList()
    private var lblTitle: TextView? = null
    private var rvList: RecyclerView? = null
    private val labelUser = "user"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        lblTitle = findViewById(R.id.lblTitle)
        rvList = findViewById(R.id.rvUsers)

        initUI()
    }

    private fun initUI() {

        RetrofitManager.initialize()
        var userService = RetrofitManager.getUserService().getUsers()
        userService.enqueue(this)
    }

    override fun onListItemClick(user: User) {

        var intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(labelUser, user)
        startActivity(intent)
    }

    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

        if (response.body()?.total!! > 0) {
            usersList = response.body()?.data!!
            rvList?.layoutManager = LinearLayoutManager(this)
            rvList?.adapter = UserListAdapter(usersList, this, this)
        }
    }
}