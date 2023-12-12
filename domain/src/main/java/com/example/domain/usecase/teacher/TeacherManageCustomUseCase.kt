package com.example.domain.usecase.teacher

import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageCustomUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun getProgressData() = teacherRepo.getProgress()
    suspend fun getScaleData() = teacherRepo.getScales()
    suspend fun getCategoryData() = teacherRepo.getCategories()
}