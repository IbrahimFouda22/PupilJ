package com.pupilJ.jk.fragments.teacher.home.student.room

import com.pupilJ.domain.models.Classes

class RoomHomeTeacherOnClickListener(private val onClickListener: (classes: Classes) -> Unit) {
    fun onClick(classes: Classes) = onClickListener(classes)
}