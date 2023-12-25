package com.pupilJ.jk.fragments.teacher.home.reminder

class ReminderOnClickListener(private val onClickListener: (reminderId: Int, position: Int) -> Unit) {
    fun onClick(reminderId: Int,position: Int,) = onClickListener(reminderId,position)
}