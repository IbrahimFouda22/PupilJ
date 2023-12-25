package com.pupilJ.jk.fragments.teacher.home.message.parent

import com.pupilJ.domain.models.Parent

class ParentsOnClickListener(private val onClickListener: (parent: Parent) -> Unit) {
    fun onClick(parent: Parent) = onClickListener(parent)
}