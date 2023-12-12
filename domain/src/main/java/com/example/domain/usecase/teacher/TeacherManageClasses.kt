package com.example.domain.usecase.teacher

import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageClasses @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun getClasses() = teacherRepo.getClasses()
    suspend fun getChildrenOfClasses(classId:Int) = teacherRepo.getChildrenOfClasses(classId)
}