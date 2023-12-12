package com.example.data.dto

data class ChildrenActivityDto(
    val `data`: ChildrenActivityDtoData
)


data class ChildrenActivityDtoData(
    val current_page: Int,
    val `data`: List<DataX>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)


data class DataX(
    val activity_date: String,
    val activity_type: String,
    val additional_fields: AdditionalFields?,
    val category: String?,
    val category_id: Int,
    val child_id: Int,
    val created_at: String,
    val id: Int,
    val image: String?,
    val meal_items: List<MealItem>?,
    val meal_type: MealType,
    val meal_type_id: Int,
    val mile_stone_id: Int,
    val milestone: String?,
    val notes: String,
    val progress: String?,
    val progress_id: Int?,
    val scale: String?,
    val scale_id: Int?,
    val school_id: Int?,
    val staff_only: Boolean,
    val temperature: String?,
    val updated_at: String,
    val video: String?
)
data class AdditionalFields(
    val eat_type: String?=null,
    val food_type: String?=null,
    val nap_type: String?=null,
    val waste_shape: String?=null,
    val waste_type: String?=null,
    val type:String
)

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)

data class MealItem(
    val created_at: String,
    val id: Int,
    val meal_type_id: Int,
    val name: String,
    val name_ar: String,
    val name_en: String,
    val pivot: Pivot,
    val school_id: Int,
    val updated_at: String
)


data class MealType(
    val created_at: String,
    val id: Int,
    val name: String,
    val name_ar: String,
    val name_en: String,
    val school_id: Int,
    val updated_at: String
)