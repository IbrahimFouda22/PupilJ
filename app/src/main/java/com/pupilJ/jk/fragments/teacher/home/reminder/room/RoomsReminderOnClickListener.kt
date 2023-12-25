package com.pupilJ.jk.fragments.teacher.home.reminder.room

import com.pupilJ.domain.models.Classes

class RoomsReminderOnClickListener(private val onClickListener: (classes: Classes) -> Unit) {
    fun onClick(classes: Classes) = onClickListener(classes)
}