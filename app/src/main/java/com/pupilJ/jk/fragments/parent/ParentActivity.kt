package com.pupilJ.jk.fragments.parent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ParentActivity : AppCompatActivity() {
    //private lateinit var networkReceiver: NetworkReceiver
    private lateinit var token: String
    private lateinit var actor: String
    private lateinit var bottomNavParent:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actor = intent.getStringExtra("actor").toString()
        bottomNavParent = findViewById(R.id.bottomNavParent)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_Parent_Container) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            bottomNavParent.menu.clear()
            if(actor == "parent") {
                val graph = inflater.inflate(R.navigation.nav_parent)
                navHostFragment.navController.graph = graph
                bottomNavParent.inflateMenu(R.menu.menu_parent)
                bottomNavParent.setupWithNavController(navHostFragment.navController)
            }
            else {
                val graph = inflater.inflate(R.navigation.nav_teacher)
                navHostFragment.navController.graph = graph
                bottomNavParent.inflateMenu(R.menu.menu_teacher)
                bottomNavParent.setupWithNavController(navHostFragment.navController)
            }


//        try {
//            networkReceiver = NetworkReceiver()
//            internetStatus()
//
//        }catch (e:NoInternetException){
//            Toast.makeText(applicationContext,e.message, Toast.LENGTH_SHORT).show()
//        }

//        networkReceiver = NetworkReceiver()
//        internetStatus()

        token = intent.getStringExtra("token").toString()

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