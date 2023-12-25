package com.pupilJ.data.dto

data class TeacherDto(
    val data:List<TeacherDtoData>
)
data class TeacherDtoData(
    val id: Int,
    val name: String,
    val email: String,
    val profile_photo_url: String,
    val dob: String,
)
