package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherGetChildActivityUseCase @Inject constructor(private val teacherRepo: TeacherRepo){
    suspend fun getChildrenActivityToday(id:Int, activityDate:String) = teacherRepo.getChildrenActivityToday(id,activityDate)
}