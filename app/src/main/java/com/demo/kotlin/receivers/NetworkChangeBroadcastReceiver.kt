package com.demo.kotlin.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.LocalBroadcastManager
import com.demo.kotlin.constants.AppConstants


class NetworkChangeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        var message = if (isConnectedToInternet(context)) "Connected to Internet" else "Disconnected from Internet"

        var intent = Intent()
        intent.action = AppConstants.networkConnectivity
        intent.putExtra(AppConstants.message, message)

        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intent)
    }

    private fun isConnectedToInternet(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}