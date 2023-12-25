package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageClasses @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun getClasses() = teacherRepo.getClasses()
    suspend fun getChildrenOfClasses(classId:Int) = teacherRepo.getChildrenOfClasses(classId)

    suspend fun getParentsOfClass(classId:Int) = teacherRepo.getParentsOfClass(classId)
}