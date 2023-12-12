package com.example.data.dto


data class LoginDto(
    val `data`: Data
)
data class Data(
    val address: String,
    val class_id: Any,
    val created_at: String,
    val current_team_id: Any,
    val deleted_at: Any,
    val dob: String,
    val email: String,
    val email_verified_at: Any,
    val grade_id: Any,
    val id: Int,
    val name: String,
    val national_id: String,
    val parent_id: Any,
    val payment_progress: String,
    val phone_number: String,
    val profile_photo_path: Any,
    val profile_photo_url: String,
    val school: School,
    val school_user_id: Any,
    val token: String,
    val two_factor_confirmed_at: Any,
    val updated_at: String
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