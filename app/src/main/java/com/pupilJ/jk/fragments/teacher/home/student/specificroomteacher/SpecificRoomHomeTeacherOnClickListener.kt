package com.pupilJ.jk.fragments.teacher.home.student.specificroomteacher

import com.pupilJ.domain.models.ChildrenTeacher

class SpecificRoomHomeTeacherOnClickListener(private val onClickListener: (childrenTeacher: ChildrenTeacher) -> Unit) {
    fun onClick(childrenTeacher: ChildrenTeacher) = onClickListener(childrenTeacher)
}