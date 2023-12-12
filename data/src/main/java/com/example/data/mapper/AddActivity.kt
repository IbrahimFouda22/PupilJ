package com.example.data.mapper

import com.example.data.dto.AddActivityDto
import com.example.domain.models.AddActivity

fun AddActivityDto.toEntity():AddActivity{
    return AddActivity(
        status = status
    )
}

//fun AdditionalFields.toEntityFood():AdditionalFieldFood{
//    return AdditionalFieldFood(
//        food_type = food_type,
//        eat_type = eat_type
//    )
//}