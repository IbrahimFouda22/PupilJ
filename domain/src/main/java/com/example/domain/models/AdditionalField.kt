package com.example.domain.models

data class AdditionalFieldFood(
    var food_type:String?,
    var eat_type:String?
)

data class AdditionalFieldNap(
    var nap_type:String?,
)

data class AdditionalFieldPotty(
    var waste_type:String?,
    var waste_shape:String?,
)

data class AdditionalFieldNameToFace(
    var type:String?,
)
