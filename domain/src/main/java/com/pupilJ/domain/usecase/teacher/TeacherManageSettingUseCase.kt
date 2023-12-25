package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.models.AboutUS
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageSettingUseCase @Inject constructor(private val repo: TeacherRepo) {

    suspend fun contactUs(schoolId:Int,name: String,email: String,title: String,problem: String):ContactUs{
        validateContactData(name, email, title, problem)
        return repo.contactUs(schoolId,name, email, title, problem)
    }

    suspend fun aboutUs(schoolId: Int ) = repo.aboutUs(schoolId)

    private fun validateContactData(name: String,email: String,title: String,problem: String){
        if(name.isNullOrEmpty())
            throw EmptyDataException("Please Enter Your Name")
        if(email.isNullOrEmpty())
            throw EmptyDataException("Please Enter Your Email")
        if(!email.contains("@"))
            throw InvalidData("Invalid Email format")
        if(title.isNullOrEmpty())
            throw EmptyDataException("Please Enter The Title")
        if(problem.isNullOrEmpty())
            throw EmptyDataException("Please Enter Your Message")
    }
}