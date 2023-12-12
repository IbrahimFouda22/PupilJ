package com.example.data.dto

data class ProfileDto(
    val `data`: DataProfile
)

data class DataProfile(
    val dob: String,
    val email: String,
    val national_id: String,
    val phone_number: String,
    val name: String,
    val profile_photo_path: String
)