package com.example.domain.usecase.teacher

import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageMileStonesUseCase @Inject constructor(private val repo: TeacherRepo) {
    suspend fun getMileStone() = repo.getMileStones()
}