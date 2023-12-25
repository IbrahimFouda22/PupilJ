package com.pupilJ.jk.fragments.teacher.home.room

import com.pupilJ.domain.models.Classes

class RoomsOnClickListener(private val onClickListener: (classes: Classes) -> Unit) {
    fun onClick(classes: Classes) = onClickListener(classes)
}