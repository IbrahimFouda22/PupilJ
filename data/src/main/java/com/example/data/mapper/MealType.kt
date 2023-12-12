package com.example.data.mapper

import com.example.data.dto.MealTypeDto
import com.example.data.dto.MealTypeDtoData
import com.example.domain.models.MealType

fun MealTypeDto.toEntity(): List<MealType> {
    return data.map {
        mealTypeToEntity(it)
    }
}

fun mealTypeToEntity(mealTypeDtoData: MealTypeDtoData): MealType {
    return MealType(
        id = mealTypeDtoData.id,
        name = mealTypeDtoData.name,
        nameAr = mealTypeDtoData.name_ar,
        nameEn =mealTypeDtoData.name_en,
        schoolId =mealTypeDtoData.school_id
    )
}