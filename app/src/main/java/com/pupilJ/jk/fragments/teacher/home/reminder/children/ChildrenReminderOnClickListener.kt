package com.pupilJ.jk.fragments.teacher.home.reminder.children

import com.pupilJ.domain.models.ChildrenTeacher

class ChildrenReminderOnClickListener(private val onClickListener: (childrenTeacher: ChildrenTeacher) -> Unit) {
    fun onClick(childrenTeacher: ChildrenTeacher) = onClickListener(childrenTeacher)
}