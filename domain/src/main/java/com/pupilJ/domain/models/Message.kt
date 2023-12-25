package com.pupilJ.domain.models

data class Message(
    val userId:Int,
    val msg:String,
    val msgTime:String?=null
)
