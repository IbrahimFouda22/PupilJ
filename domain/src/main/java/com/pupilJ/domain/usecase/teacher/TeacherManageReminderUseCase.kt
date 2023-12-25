package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.models.AddReminder
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageReminderUseCase @Inject constructor(private val repo: TeacherRepo) {
    suspend fun getReminder() = repo.getReminder()
    suspend fun deleteReminder(reminderId: Int) = repo.deleteReminder(reminderId)
    suspend fun addReminder(
        activityType: String?,
        activityDate: String,
        childId: Int,
        repeatId: Int,
    ): AddReminder {
        validateReminderData(activityType)
        return repo.addReminder(activityType!!, activityDate, childId, repeatId)
    }

    private fun validateReminderData(
        activityType: String?,
    ) {
        if (activityType.isNullOrEmpty())
            throw EmptyDataException("Please choose activity")
    }
}