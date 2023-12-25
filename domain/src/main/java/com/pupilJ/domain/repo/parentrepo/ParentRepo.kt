package com.pupilJ.domain.repo.parentrepo


import com.pupilJ.domain.models.AboutUS
import com.pupilJ.domain.models.Children
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.Teacher
import com.pupilJ.domain.models.User


interface ParentRepo {
    suspend fun loginParent(nationalId: String, password: String): User
    suspend fun getChildren(): List<Children>
    suspend fun getTeacherOfChildren(childId: Int): List<Teacher>
    suspend fun getChildrenActivity(id: Int): List<ChildrenActivity>

    suspend fun contactUs(
        schoolId: Int,
        name: String,
        email: String,
        title: String,
        problem: String
    ): ContactUs

    suspend fun aboutUs(
        schoolId: Int,
    ): AboutUS
}