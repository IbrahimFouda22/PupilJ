package com.example.domain.repo.parentrepo

import com.example.domain.models.Children
import com.example.domain.models.User
import com.example.domain.models.UserProfile


interface ParentRepo {
    suspend fun loginParent(nationalId: String, password: String):User
    //suspend fun getProfile(token:String): UserProfile
    suspend fun getChildren(): List<Children>
}