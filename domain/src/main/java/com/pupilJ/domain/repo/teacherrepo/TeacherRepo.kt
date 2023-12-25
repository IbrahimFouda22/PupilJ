package com.pupilJ.domain.repo.teacherrepo


import com.pupilJ.domain.models.AddActivity
import com.pupilJ.domain.models.AddReminder
import com.pupilJ.domain.models.AdditionalFieldFood
import com.pupilJ.domain.models.AdditionalFieldNameToFace
import com.pupilJ.domain.models.AdditionalFieldNap
import com.pupilJ.domain.models.AdditionalFieldPotty
import com.pupilJ.domain.models.Attendance
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.domain.models.Classes
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.Custom
import com.pupilJ.domain.models.DeleteReminder
import com.pupilJ.domain.models.MealType
import com.pupilJ.domain.models.Message
import com.pupilJ.domain.models.Parent
import com.pupilJ.domain.models.PhotoVideo
import com.pupilJ.domain.models.Reminder
import com.pupilJ.domain.models.StartRoom
import com.pupilJ.domain.models.User
import java.io.File

interface TeacherRepo {
    suspend fun loginTeacher(nationalId: String, password: String): User
    suspend fun getClasses(): List<Classes>
    suspend fun getChildrenOfClasses(id: Int): List<ChildrenTeacher>
    suspend fun getParentsOfClass(classId: Int): List<Parent>
    suspend fun getMessages(chatId: String): List<Message>
    suspend fun startRoom(userId: Int): StartRoom
    suspend fun sendMessage(msg: String,chatId: String): Message
    suspend fun uploadImage(file: File, activityType: String, childId: String): PhotoVideo
    suspend fun uploadVideo(file: File, activityType: String, childId: String): PhotoVideo
    suspend fun getChildrenActivityToday(id: Int, activityDate: String): List<ChildrenActivity>

    suspend fun contactUs(
        schoolId: Int,
        name: String,
        email: String,
        title: String,
        problem: String
    ): ContactUs

    suspend fun setAttendance(
        attendType: String,
        attendDate: String,
        children: List<Int>
    ): Attendance

    suspend fun getMealType(): List<MealType>

    suspend fun getReminder(): List<Reminder>

    suspend fun addReminder(
        activityType: String,
        activityDate: String,
        childId: Int,
        repeatId: Int
    ): AddReminder

    suspend fun deleteReminder(
        reminderId: Int,
    ): DeleteReminder

    suspend fun getMealItem(mealTypeId: Int): List<MealType>
    suspend fun addFoodActivity(
        activityType: String,
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
        mealId: Int,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity

    suspend fun addNapActivity(
        activityType: String,
        additionalFieldNap: AdditionalFieldNap,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity

    suspend fun addPottyActivity(
        activityType: String,
        additionalFieldPotty: AdditionalFieldPotty,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity

    suspend fun addNoteMedIncidentsActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity

    suspend fun addAchievementActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        image: String?
    ): AddActivity

    suspend fun getProgress(): Custom
    suspend fun getScales(): Custom
    suspend fun getCategories(): Custom
    suspend fun getMileStones(): Custom

    suspend fun addCustomActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        categoryId: Int,
        progressId: Int,
        scaleId: Int,
    ): AddActivity

    suspend fun addObservationActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        mileStoneId: Int,
        staffOnly: Int,
    ): AddActivity

    suspend fun addHealthCheckActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        temperature: String,
    ): AddActivity

    suspend fun addNameToFaceActivity(
        activityType: String,
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        childId: Int,
        activityDate: String,
        notes: String,
        image: String,
    ): AddActivity

}