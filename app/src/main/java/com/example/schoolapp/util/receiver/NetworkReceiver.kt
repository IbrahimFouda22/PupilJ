package com.example.schoolapp.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.domain.models.NoInternetException


class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val status = getNetworkInfo(context)
        if(status == "connected")
            Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "disconnected", Toast.LENGTH_SHORT).show()

    }
    private fun getNetworkInfo(context: Context?): String{
        lateinit var status : String
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetwork
        return if(networkInfo!=null){
            status = "connected"
            status
        }else{
            status = "disconnected"
            status
        }
    }
}
