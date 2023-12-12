package com.example.data.dto

data class ChildrenDto(
    val `data`: List<DataChildren>
)
data class DataChildren(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val name: String,
    val profile_photo_url: String,
    val profile_picture: String,
    val type: String
)