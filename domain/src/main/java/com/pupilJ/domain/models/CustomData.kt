package com.pupilJ.domain.models

data class Custom (
    val status:String,
    val data: List<CustomData>
)
data class CustomData(
    val id:Int,
    val name: String,
    val name_ar: String,
    val name_en: String,
)