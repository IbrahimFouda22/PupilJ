package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity

import com.pupilJ.domain.models.ActivityType

class AddActivityOnClickListener(private val onClickListener: (activityType: ActivityType) -> Unit) {
    fun onClick(activityType: ActivityType) = onClickListener(activityType)
}