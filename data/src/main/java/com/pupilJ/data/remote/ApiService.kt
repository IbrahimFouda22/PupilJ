package com.pupilJ.data.remote

import com.pupilJ.data.dto.AddActivityDto
import com.pupilJ.data.dto.AddReminderDto
import com.pupilJ.data.dto.AttendanceDto
import com.pupilJ.data.dto.ChildrenActivityDto
import com.pupilJ.data.dto.ChildrenDto
import com.pupilJ.data.dto.ChildrenDtoTeacher
import com.pupilJ.data.dto.ClassesDto
import com.pupilJ.data.dto.ContactUsDto
import com.pupilJ.data.dto.DeleteReminderDto
import com.pupilJ.data.dto.LoginDto
import com.pupilJ.data.dto.MealTypeDto
import com.pupilJ.data.dto.MessageDto
import com.pupilJ.data.dto.ParentDto
import com.pupilJ.data.dto.PhotoVideoDto
import com.pupilJ.data.dto.ReminderDto
import com.pupilJ.data.dto.SendMessageDto
import com.pupilJ.data.dto.StartRoomDto
import com.pupilJ.data.dto.TeacherDto
import com.pupilJ.domain.models.Custom
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("parent/login")
    suspend fun loginParent(
        @Field("national_id") nationalId: String,
        @Field("password") password: String
    ): Response<LoginDto>


    @GET("parent/nursery/children")
    suspend fun getChildren(
    ): Response<ChildrenDto>


    @FormUrlEncoded
    @POST("teacher/login")
    suspend fun loginTeacher(
        @Field("national_id") nationalId: String,
        @Field("password") password: String
    ): Response<LoginDto>

    @GET("teacher/nursery/classes")
    suspend fun getClasses(
    ): Response<ClassesDto>

    @GET("teacher/nursery/classes/{id}/children")
    suspend fun getChildrenOfClass(
        @Path("id") id: Int,
    ): Response<ChildrenDtoTeacher>

    @Multipart
    @POST("teacher/nursery/child/activity")
    suspend fun uploadPhoto(
        @Part("activity_type") activityType: RequestBody,
        @Part("child_id") childId: RequestBody,
        @Part photo: MultipartBody.Part
    ): Response<PhotoVideoDto>

    @Multipart
    @POST("teacher/nursery/child/activity")
    suspend fun uploadVideo(
        @Part("activity_type") activityType: RequestBody,
        @Part video: MultipartBody.Part,
        @Part("child_id") childId: RequestBody
    ): Response<PhotoVideoDto>

    @GET("teacher/nursery/child/{id}/activity")
    suspend fun getActivityOfChildrenToday(
        @Path("id") id: Int,
        @Query("activity_date") activityDate: String
    ): Response<ChildrenActivityDto>

    @GET("teacher/nursery/child/{id}/activity")
    suspend fun getActivityOfChildren(
        @Path("id") id: Int,
    ): Response<ChildrenActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/children/attendance")
    suspend fun setAttendance(
        @Field("attend_type") attendType: String,
        @Field("attend_date") attendDate: String,
        @Field("children[]") children: List<Int>,
    ): Response<AttendanceDto>

    @GET("teacher/nursery/meal-types")
    suspend fun getMealTypes(
    ): Response<MealTypeDto>

    @GET("teacher/nursery/meal-items/{id}")
    suspend fun getMealItems(
        @Path("id") mealTypeId: Int
    ): Response<MealTypeDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addFoodActivity(
        @Field("activity_type") activityType: String,
        @Field("additional_fields[food_type]") foodType: String,
        @Field("additional_fields[eat_type]") eatType: String,
        @Field("meal_items[]") mealItems: List<Int>,
        @Field("meal_type_id") mealId: Int,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/reminders")
    suspend fun addReminder(
        @Field("type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("reminder_date") activityDate: String,
        @Field("repeat_until_checkout") repeatId: Int
    ): Response<AddReminderDto>

    @DELETE("teacher/nursery/reminders/{id}")
    suspend fun deleteReminder(
        @Path("id") reminderId: Int
    ): Response<DeleteReminderDto>


    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addNapActivity(
        @Field("activity_type") activityType: String,
        @Field("additional_fields[nap_type]") foodType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addPottyActivity(
        @Field("activity_type") activityType: String,
        @Field("additional_fields[waste_type]") wasteType: String,
        @Field("additional_fields[waste_shape]") wasteShape: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?
    ): Response<AddActivityDto>


    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addNoteMedIncidentActivity(
        @Field("activity_type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addAchievementActivity(
        @Field("activity_type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?,
        @Field("image") image: String
    ): Response<AddActivityDto>

    @GET("teacher/nursery/progress")
    suspend fun getProgress(
    ): Response<Custom>

    @GET("teacher/nursery/categories")
    suspend fun getCategories(
    ): Response<Custom>

    @GET("teacher/nursery/scales")
    suspend fun getScales(
    ): Response<Custom>

    @GET("teacher/nursery/mile-stones")
    suspend fun getMileStones(
    ): Response<Custom>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addCustomActivity(
        @Field("activity_type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?,
        @Field("category_id") categoryId: Int,
        @Field("progress_id") progressId: Int,
        @Field("scale_id") scaleId: Int,
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addObservationActivity(
        @Field("activity_type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?,
        @Field("mile_stone_id") mileStoneId: Int,
        @Field("staff_only") staffOnly: Int,
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addHealthCheckActivity(
        @Field("activity_type") activityType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String?,
        @Field("temperature") temperature: String,
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("teacher/nursery/child/activity")
    suspend fun addNameToFaceActivity(
        @Field("activity_type") activityType: String,
        @Field("additional_fields[type]") nameToFaceType: String,
        @Field("child_id") childId: Int,
        @Field("activity_date") activityDate: String,
        @Field("notes") notes: String,
        @Field("photo") photo: String,
    ): Response<AddActivityDto>

    @FormUrlEncoded
    @POST("school/{id}/contact-us")
    suspend fun contactUs(
        @Path("id") schoolId: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("title") title: String,
        @Field("message") msg: String
    ): Response<ContactUsDto>

    @FormUrlEncoded
    @POST("teacher/chat/start")
    suspend fun startRoom(
        @Field("user_id") userId: Int
    ): Response<StartRoomDto>

    @GET("user/chat/message/{chatID}")
    suspend fun getLastMessages(
        @Path("chatID") chatId: String
    ): Response<MessageDto>

    @FormUrlEncoded
    @POST("user/chat/message")
    suspend fun sendMessage(
        @Field("msg") msg: String,
        @Field("chat_id") chatId: String
    ): Response<SendMessageDto>

    @GET("teacher/nursery/classes/{classId}/parents")
    suspend fun getParentsOfClass(
        @Path("classId")classId:Int
    ): Response<ParentDto>

    @GET("parent/nursery/children/{childId}/teachers")
    suspend fun getTeachersOfChildren(
        @Path("childId")childId:Int
    ): Response<TeacherDto>

    @GET("teacher/nursery/reminders")
    suspend fun getReminders(): Response<ReminderDto>
}