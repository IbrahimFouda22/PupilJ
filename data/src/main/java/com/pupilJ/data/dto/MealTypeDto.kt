package com.pupilJ.data.dto

data class MealTypeDto(
    val `data`: List<MealTypeDtoData>,
    val status: String
)

data class MealTypeDtoData(
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val name_ar: String,
    val name_en: String,
    val school_id: Int,
    val updated_at: String
)