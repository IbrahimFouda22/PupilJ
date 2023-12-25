package com.pupilJ.data.mapper

import com.pupilJ.data.dto.AddActivityDto
import com.pupilJ.domain.models.AddActivity


fun AddActivityDto.toEntity(): AddActivity {
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