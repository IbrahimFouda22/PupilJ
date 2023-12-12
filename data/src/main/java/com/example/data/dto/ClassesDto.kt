package com.example.data.dto

data class ClassesDto(
    val `data`: List<ClassesData>,
    val msg: String
)

data class ClassesData(
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val name_ar: String,
    val name_en: String,
    val school_id: Int,
    val updated_at: String
)