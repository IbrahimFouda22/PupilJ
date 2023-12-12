package com.example.schoolapp.fragments.parent.children.mychildrens

import com.example.domain.models.Children

class OnClickListener(private val onClickListener: (children: Children) -> Unit) {
    fun onClick(children: Children) = onClickListener(children)
}