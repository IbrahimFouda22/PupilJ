package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity

import com.example.domain.models.ActivityType

class AddActivityOnClickListener(private val onClickListener: (activityType: ActivityType) -> Unit) {
    fun onClick(activityType: ActivityType) = onClickListener(activityType)
}