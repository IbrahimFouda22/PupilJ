package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherAuthUseCase @Inject constructor (private val teacherRepo: TeacherRepo) {
    suspend fun login(nationalId: String, password: String) = teacherRepo.loginTeacher(nationalId, password)

}