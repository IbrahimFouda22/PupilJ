package com.example.schoolapp.fragments.teacher.home.room.specificroom

import com.example.domain.models.ChildrenTeacher

class SpecificRoomOnClickListener(private val onClickListener: (childrenTeacher: ChildrenTeacher) -> Unit) {
    fun onClick(childrenTeacher: ChildrenTeacher) = onClickListener(childrenTeacher)
}