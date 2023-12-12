package com.example.schoolapp.fragments.teacher.home.room

import com.example.domain.models.Classes

class RoomsOnClickListener(private val onClickListener: (classes: Classes) -> Unit) {
    fun onClick(classes: Classes) = onClickListener(classes)
}