package com.example.domain.repo.teacherrepo

import android.net.Uri
import com.example.domain.models.AddActivity
import com.example.domain.models.AdditionalFieldFood
import com.example.domain.models.AdditionalFieldNameToFace
import com.example.domain.models.AdditionalFieldNap
import com.example.domain.models.AdditionalFieldPotty
import com.example.domain.models.Attendance
import com.example.domain.models.ChildrenActivity
import com.example.domain.models.ChildrenTeacher
import com.example.domain.models.Classes
import com.example.domain.models.Custom
import com.example.domain.models.CustomData
import com.example.domain.models.MealType
import com.example.domain.models.PhotoVideo
import com.example.domain.models.User
import java.io.File

interface TeacherRepo {
    suspend fun loginTeacher(nationalId: String, password: String): User
    suspend fun getClasses(): List<Classes>
    suspend fun getChildrenOfClasses(id: Int): List<ChildrenTeacher>
    suspend fun uploadImage(file: File, activityType: String, childId: String): PhotoVideo
    suspend fun uploadVideo(file: File, activityType: String, childId: String): PhotoVideo
    suspend fun getChildrenActivity(id: Int): List<ChildrenActivity>
    suspend fun setAttendance(
        attendType: String,
        attendDate: String,
        children: List<Int>
    ): Attendance

    suspend fun getMealType(): List<MealType>
    suspend fun getMealItem(mealTypeId: Int): List<MealType>
    suspend fun addFoodActivity(
        activityType: String,
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
        mealId: Int,
        childId: Int,
        activityDate: String,
        notes:String?
    ):AddActivity

    suspend fun addNapActivity(
        activityType: String,
        additionalFieldNap: AdditionalFieldNap,
        childId: Int,
        activityDate: String,
        notes:String?
    ):AddActivity

    suspend fun addPottyActivity(
        activityType: String,
        additionalFieldPotty: AdditionalFieldPotty,
        childId: Int,
        activityDate: String,
        notes:String?
    ):AddActivity

    suspend fun addNoteMedIncidentsActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?
    ):AddActivity

    suspend fun addAchievementActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        image:String?
    ):AddActivity

    suspend fun getProgress():Custom
    suspend fun getScales(): Custom
    suspend fun getCategories():Custom
    suspend fun getMileStones():Custom

    suspend fun addCustomActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        categoryId:Int,
        progressId:Int,
        scaleId:Int,
    ):AddActivity

    suspend fun addObservationActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        mileStoneId:Int,
        staffOnly:Int,
    ):AddActivity

    suspend fun addHealthCheckActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        temperature:String,
    ):AddActivity

    suspend fun addNameToFaceActivity(
        activityType: String,
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        childId: Int,
        activityDate: String,
        notes:String,
        image:String,
    ):AddActivity

}