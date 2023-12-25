package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.models.Message
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageMessagesUseCase @Inject constructor (private val teacherRepo: TeacherRepo) {
    suspend fun startRoom(userId:Int) = teacherRepo.startRoom(userId)
    suspend fun getMessages(chatId:String) = teacherRepo.getMessages(chatId)

    suspend fun senMessage(msg:String?,chatId: String):Message{
        validateMessage(msg)
        return teacherRepo.sendMessage(msg!!, chatId)
    }

    private fun validateMessage(msg: String?){
        if(msg.isNullOrEmpty())
            throw EmptyDataException("Please Enter Message")
    }
}