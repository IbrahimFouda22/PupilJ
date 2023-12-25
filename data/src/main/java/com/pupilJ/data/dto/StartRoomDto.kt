package com.pupilJ.data.dto

data class StartRoomDto(
    val data: StartRoomData,
)


data class StartRoomData(
    val chat_id: String,
    val created_at: String,
    val created_by: String,
    val employee_id: Int,
    val id: Int,
    val school_id: Int,
    val updated_at: String,
    val user_id: Int
)
