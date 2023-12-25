package com.pupilJ.domain.repo.parentrepo


import com.pupilJ.domain.models.Children
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.models.Teacher
import com.pupilJ.domain.models.User


interface ParentRepo {
    suspend fun loginParent(nationalId: String, password: String): User
    suspend fun getChildren(): List<Children>
    suspend fun getTeacherOfChildren(childId: Int): List<Teacher>

    suspend fun getChildrenActivity(id: Int): List<ChildrenActivity>

}