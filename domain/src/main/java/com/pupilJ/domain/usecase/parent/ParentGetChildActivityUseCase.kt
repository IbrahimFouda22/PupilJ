package com.pupilJ.domain.usecase.parent

import com.pupilJ.domain.repo.parentrepo.ParentRepo
import javax.inject.Inject

class ParentGetChildActivityUseCase @Inject constructor(private val repo: ParentRepo){
    suspend fun getChildrenActivity(id:Int) = repo.getChildrenActivity(id)
}