package com.pupilJ.jk.fragments.teacher.home.room.specificroom.attendance

import com.pupilJ.domain.models.ChildrenTeacher

class AttendanceOnClickListener(private val onClickListener: (children: ChildrenTeacher) -> Unit) {
    fun onClick(children: ChildrenTeacher) = onClickListener(children)
}