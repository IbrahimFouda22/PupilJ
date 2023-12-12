package com.example.domain.usecase.parent

import com.example.domain.repo.parentrepo.ParentRepo
import javax.inject.Inject

class ParentChildrenUseCase @Inject constructor(private val parentRepo: ParentRepo) {
    suspend fun getChildren() = parentRepo.getChildren()
}