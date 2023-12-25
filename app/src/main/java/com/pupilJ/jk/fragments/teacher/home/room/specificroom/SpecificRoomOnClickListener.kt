package com.pupilJ.jk.fragments.teacher.home.room.specificroom

import com.pupilJ.domain.models.ChildrenTeacher

class SpecificRoomOnClickListener(private val onClickListener: (childrenTeacher: ChildrenTeacher) -> Unit) {
    fun onClick(childrenTeacher: ChildrenTeacher) = onClickListener(childrenTeacher)
}