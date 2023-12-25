package com.pupilJ.jk.fragments.parent.children.chat.teacher

import com.pupilJ.domain.models.Teacher

class TeachersOnClickListener(private val onClickListener: (teacher: Teacher) -> Unit) {
    fun onClick(teacher: Teacher) = onClickListener(teacher)
}