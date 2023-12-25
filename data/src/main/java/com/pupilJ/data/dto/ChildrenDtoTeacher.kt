package com.pupilJ.data.dto

data class ChildrenDtoTeacher(
    val `data`: List<ChildrenDtoTeacherData>,
    val msg: String
)

data class ChildrenDtoTeacherData(
    val address_1: String?,
    val address_2: String?,
    val city: String,
    val created_at: String,
    val deleted_at: String?,
    val dob: String,
    val doctor_name: String?,
    val doctor_phone: String?,
    val first_name: String?,
    val id: Int,
    val last_name: String?,
    val meals_allergies: String?,
    val medicine: String?,
    val name: String,
    val notes: String?,
    val nursery_class_id: Int,
    val parent_id: Int,
    val profile_photo_url: String,
    val profile_picture: String,
    val school_id: Int,
    val status: String?,
    val type: String,
    val updated_at: String,
    val attend_today: Boolean,
    val father_number: String?,
    val mother_number: String?,
    val pickup_time: String?,
    val check_in: Boolean,
    val check_out: Boolean,
    )