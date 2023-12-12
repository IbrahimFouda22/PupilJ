package com.example.data.remote

import com.example.data.dto.LoginDto

interface IRemoteDataSource {
    suspend fun login(nationalId: String, password: String): LoginDto
//    suspend fun getProfile(token: String): ProfileModel
//    suspend fun getChildren(nationalId: String, password: String): LoginDto
}