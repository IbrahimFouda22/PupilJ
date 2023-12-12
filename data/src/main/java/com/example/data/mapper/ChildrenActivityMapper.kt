package com.example.data.mapper

import com.example.data.dto.AdditionalFields
import com.example.data.dto.ChildrenActivityDto
import com.example.data.dto.DataX
import com.example.data.dto.MealItem
import com.example.domain.models.ChildrenActivity


fun childrenActivityDtoToEntity(childrenActivityDto: ChildrenActivityDto) :List<ChildrenActivity>{
    return childrenActivityDto.data.data.map {
        childrenActivityToEntity(it)
    }
}
fun childrenActivityToEntity(data : DataX) : ChildrenActivity{
    return ChildrenActivity(
        activityType = data.activity_type,
        activityDate = data.activity_date,
        txtDescription = data.notes,
        image = data.image,
        napType = data.additional_fields?.nap_type,
        foodType = data.additional_fields?.food_type,
        eatType = data.additional_fields?.eat_type,
        wasteShape = data.additional_fields?.waste_shape,
        wasteType = data.additional_fields?.waste_type,
        createdAt = data.created_at,
        temperature = data.temperature,
        meals = data.meal_items?.let { mealItemsData(it) },
        nameToFaceType = data.additional_fields?.type,
        id = data.id
    )
}

fun mealItemsData(mealItem: List<MealItem>) :List<String>{
    return mealItem.map {
        mealItemToString(it)
    }
}
fun mealItemToString(mealItem: MealItem):String{
    return mealItem.name
}

