package com.example.domain.models


data class User(
    val address: String,
    val email: String,
    val id: Int,
    val name: String,
    val national_id: String,
    val phone_number: String?=null,
    val profile_photo_url: String,
    val schoolId: Int,
    val token: String,
)
data class Pivot(
    val school_id: Int,
    val user_id: Int
)
data class School(
    val description: String,
    val id: Int,
    val logo: String,
    val name: String,
    val pivot: Pivot
)