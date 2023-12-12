package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChildrenTeacher(
    val address1: String?,
    val address2: String?,
    val city: String,
    val dob: String,
    val doctorName: String?,
    val doctorPhone: String?,
    val firstName: String?,
    val id: Int,
    val lastName: String?,
    val name: String?,
    val notes: String?,
    val nurseryClassId: Int,
    val parentId: Int,
    val profilePhotoUrl: String,
    val schoolId: Int,
    val status: String?,
    val medicine: String?,
    val allergies: String?,
    val fatherNum: String?,
    val motherNumber: String?,
    val pickUpTime: String?,
    val attendToday: Boolean
) : Parcelable{
    @IgnoredOnParcel
    var isSelected:Boolean = false
}