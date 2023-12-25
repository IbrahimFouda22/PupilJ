package com.pupilJ.domain.usecase.parent

import com.pupilJ.domain.repo.parentrepo.ParentRepo
import javax.inject.Inject

class ParentAuthUseCase @Inject constructor (private val parentRepo: ParentRepo) {
    suspend fun login(nationalId: String, password: String) = parentRepo.loginParent(nationalId, password)

}
