package com.example.domain.usecase.teacher

import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherGetChildActivityUseCase @Inject constructor(private val teacherRepo: TeacherRepo){
    suspend fun getChildrenActivity(id:Int) = teacherRepo.getChildrenActivity(id)
}