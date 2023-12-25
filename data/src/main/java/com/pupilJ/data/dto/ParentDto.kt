package com.pupilJ.data.dto

data class ParentDto(
    val data:List<ParentDtoData>
)
data class ParentDtoData(
    val id: Int,
    val name: String,
    val email: String,
    val profile_photo_url: String,
    val dob: String,
)
