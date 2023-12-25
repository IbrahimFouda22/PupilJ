package com.pupilJ.domain.usecase.parent

import com.pupilJ.domain.repo.parentrepo.ParentRepo
import javax.inject.Inject

class ParentChildrenUseCase @Inject constructor(private val parentRepo: ParentRepo) {
    suspend fun getChildren() = parentRepo.getChildren()
    suspend fun getTeachersOfChildren(childId: Int) = parentRepo.getTeacherOfChildren(childId)
}