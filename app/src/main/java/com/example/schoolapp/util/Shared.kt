package com.example.schoolapp.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.domain.models.MealType
import com.example.schoolapp.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
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
