package com.pupilJ.domain.models

data class Reminder(
    val id: Int,
    val activityType: String,
    val reminderDate: String,
    val repeatUntilCheckout: String,
    val childId: Int,
    val photo: String,
    val childName: String,
)