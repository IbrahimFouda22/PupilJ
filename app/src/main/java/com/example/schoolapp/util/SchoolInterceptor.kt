package com.example.schoolapp.util

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SchoolInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString("token",null)
        return if(token.isNullOrEmpty()){
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ").build()
            chain.proceed(request)
        }else{

            val request = chain.request().newBuilder().header("schoolId",
                sharedPreferences.getString("schoolId","")!!
            )
                .header("Authorization", "Bearer $token").build()
            chain.proceed(request)
        }
    }

}