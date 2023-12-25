package com.pupilJ.data.dto

data class MessageDto(
    val data:List<MessageDtoData>
)
data class MessageDtoData(
    val id: Int,
    val chat_id: Int,
    val message: String,
    val from: Int,
    val created_at: String,
)
