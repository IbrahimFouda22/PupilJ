package com.pupilJ.data.dto

data class ReminderDto(
    val data:ReminderDtoDataX
)
data class ReminderDtoDataX(
    val current_page:Int,
    val data: List<ReminderDtoData>
)

data class ReminderDtoData(
    val id: Int,
    val type: String,
    val reminder_date: String,
    val repeat_until_checkout: String,
    val child_id: Int,
    val child: ChildDto,
)

data class ChildDto(
    val profile_photo_url:String,
    val name:String,
)