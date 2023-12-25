package com.pupilJ.jk

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pupilJ.jk.util.setLocal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        val lang = sharedPreferences.getString("lang","en")
        setLocal(this, lang!!)
    }

//    private fun internetStatus(){
//        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//    }
//
//    override fun onPause() {
//        super.onPause()
//        unregisterReceiver(networkReceiver)
//    }

}