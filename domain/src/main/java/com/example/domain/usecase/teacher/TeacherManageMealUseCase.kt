package com.example.domain.usecase.teacher

import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageMealUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun getMealType() = teacherRepo.getMealType()
    suspend fun getMealItems(mealTypeId:Int) = teacherRepo.getMealItem(mealTypeId)
}