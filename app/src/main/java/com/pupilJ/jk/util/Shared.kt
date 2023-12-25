package com.pupilJ.jk.util

import android.app.Activity
import android.view.WindowManager
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val BASE_URL = "http://school.ahmedsprofile.com/api/"

val token = ""
var first = ""
var last = ""
var phone = ""
var email = ""
var photo = ""
var name = ""
//to change language
fun setLocal(activity: Activity, lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val resources = activity.resources
    val config = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun changeStatusBarColor(activity: Activity,color:Int){
    val window = activity.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = color
}

fun back(navController: NavController){
    navController.popBackStack()
}

fun getTimeNow(format: String): String {
    val currentTime = Calendar.getInstance().time
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(currentTime)
}
