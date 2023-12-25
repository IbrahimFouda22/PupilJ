package com.pupilJ.jk.fragments.parent.children.mychildrens

import com.pupilJ.domain.models.Children

class OnClickListener(private val onClickListener: (children: Children) -> Unit) {
    fun onClick(children: Children) = onClickListener(children)
}