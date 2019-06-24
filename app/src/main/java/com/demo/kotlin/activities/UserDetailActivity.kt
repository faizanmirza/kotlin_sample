package com.demo.kotlin.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.demo.kotlin.R
import com.demo.kotlin.constants.AppConstants
import com.demo.kotlin.entities.User
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private var inputFeedback: EditText? = null
    private var btnSubmit: Button? = null
    private var txtName: TextView? = null
    private var txtEmail: TextView? = null
    private var networkBroadcastReceiver: NetworkBroadcastReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        inputFeedback = findViewById(R.id.inputFeedback)
        btnSubmit = findViewById(R.id.btnSubmit)
        setUI()
        setClickListeners()
    }

    override fun onResume() {
        super.onResume()

        networkBroadcastReceiver = NetworkBroadcastReceiver()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            networkBroadcastReceiver!!,
            IntentFilter(AppConstants.networkConnectivity)
        )

        Log.d(AppConstants.tag, "Register Local Network Receiver")
    }

    override fun onPause() {

        LocalBroadcastManager.getInstance(this).unregisterReceiver(networkBroadcastReceiver as BroadcastReceiver)
        Log.d(AppConstants.tag, "Unregister Local Network Receiver")
        super.onPause()
    }

    private fun setUI() {

        var user = intent.getParcelableExtra<User>(AppConstants.labelUser)
        txtName?.text = user.getName()
        txtEmail?.text = user.email
        Glide.with(this).load(user.profilePic).placeholder(R.drawable.user_placeholder).into(imgUser)
    }

    private fun setClickListeners() {
        btnSubmit?.setOnClickListener {
            onSubmit()
        }
    }

    private fun onSubmit() {

        var message = if (TextUtils.isEmpty(inputFeedback?.text.toString())) {
            "Please add your feedback for user"
        } else {
            "Thank you for submitting feedback for user"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    class NetworkBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) =
            Toast.makeText(context, intent.getStringExtra(AppConstants.message), Toast.LENGTH_SHORT).show()
    }
}