package com.example.schoolapp.fragments.teacher.home.room.specificroom.attendance

import com.example.domain.models.ChildrenTeacher

class AttendanceOnClickListener(private val onClickListener: (children: ChildrenTeacher) -> Unit) {
    fun onClick(children: ChildrenTeacher) = onClickListener(children)
}