package com.pupilJ.data.repo


import com.pupilJ.data.mapper.childrenActivityDtoToEntity
import com.pupilJ.data.mapper.childrenTeacherDtoToEntity
import com.pupilJ.data.mapper.classDtoToEntity
import com.pupilJ.data.mapper.toEntity
import com.pupilJ.data.remote.ApiService
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
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.models.MealType
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.models.PhotoVideo
import com.pupilJ.domain.models.ServerException
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.models.Message
import com.pupilJ.domain.models.Parent
import com.pupilJ.domain.models.Reminder
import com.pupilJ.domain.models.StartRoom
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class TeacherRepoImpl(private val apiService: ApiService) : TeacherRepo {
    override suspend fun loginTeacher(nationalId: String, password: String) =
        wrapApiResponse {
            apiService.loginTeacher(nationalId, password)
        }.toEntity()

    override suspend fun getClasses(): List<Classes> {
        return classDtoToEntity(wrapApiResponse {
            apiService.getClasses()
        })
    }

    override suspend fun getChildrenOfClasses(id: Int): List<ChildrenTeacher> {
        return childrenTeacherDtoToEntity(
            wrapApiResponse {
                apiService.getChildrenOfClass(id)
            }
        )
    }

    override suspend fun getParentsOfClass(classId: Int): List<Parent> {
        return wrapApiResponse {
            apiService.getParentsOfClass(classId)
        }.toEntity()
    }


    override suspend fun getMessages(chatId: String): List<Message> {
        return wrapApiResponse {
            apiService.getLastMessages(chatId)
        }.toEntity()
    }

    override suspend fun startRoom(userId: Int): StartRoom {
        return wrapApiResponse {
            apiService.startRoom(userId)
        }.toEntity()
    }

    override suspend fun sendMessage(msg: String, chatId: String): Message {
        return wrapApiResponse {
            apiService.sendMessage(msg, chatId)
        }.toEntity()
    }

    override suspend fun uploadImage(file: File, activityType: String, childId: String): PhotoVideo {
        val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
        val photo = MultipartBody.Part.createFormData("photo", file.name, requestFile)
        val activityName = RequestBody.create(MediaType.parse("text/plain"), activityType)
        val childID = RequestBody.create(MediaType.parse("text/plain"), childId)
        return wrapApiResponse {
            apiService.uploadPhoto(activityName, childID, photo)
        }.toEntity()
    }

    override suspend fun uploadVideo(
        file: File,
        activityType: String,
        childId: String
    ): PhotoVideo {
        //val file = File(uri.path!!)
        val requestFile = RequestBody.create(MediaType.parse("video/*"), file)
        val video = MultipartBody.Part.createFormData("video", file.name, requestFile)
        val activityName = RequestBody.create(MediaType.parse("text/plain"), activityType)
        val child = RequestBody.create(MediaType.parse("text/plain"), childId)
        return wrapApiResponse {
            apiService.uploadVideo(activityName, video, child)
        }.toEntity()
    }

    override suspend fun getChildrenActivityToday(
        id: Int,
        activityDate: String
    ): List<ChildrenActivity> {
        return childrenActivityDtoToEntity(
            wrapApiResponse {
                apiService.getActivityOfChildrenToday(id, activityDate)
            }
        )
    }

    override suspend fun contactUs(
        schoolId: Int,
        name: String,
        email: String,
        title: String,
        problem: String
    ): ContactUs {
        return wrapApiResponse {
            apiService.contactUs(schoolId, name, email, title, problem)
        }.toEntity()
    }

    override suspend fun setAttendance(
        attendType: String,
        attendDate: String,
        children: List<Int>
    ): Attendance {
        return wrapApiResponse {
            apiService.setAttendance(attendType, attendDate, children)
        }.toEntity()
    }

    override suspend fun getMealType(): List<MealType> {
        return wrapApiResponse {
            apiService.getMealTypes()
        }.toEntity()
    }

    override suspend fun getReminder(): List<Reminder> {
        return wrapApiResponse {
            apiService.getReminders()
        }.toEntity()
    }

    override suspend fun addReminder(
        activityType: String,
        activityDate: String,
        childId: Int,
        repeatId: Int
    ): AddReminder {
        return wrapApiResponse {
            apiService.addReminder(activityType, childId, activityDate, repeatId)
        }.toEntity()
    }

    override suspend fun deleteReminder(reminderId: Int): DeleteReminder {
        return wrapApiResponse {
            apiService.deleteReminder(reminderId)
        }.toEntity()
    }

    override suspend fun getMealItem(mealTypeId: Int): List<MealType> {
        return wrapApiResponse {
            apiService.getMealItems(mealTypeId)
        }.toEntity()
    }

    override suspend fun addFoodActivity(
        activityType: String,
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
        mealId: Int,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        return wrapApiResponse {
            apiService.addFoodActivity(
                activityType,
                additionalFieldFood.food_type!!,
                additionalFieldFood.eat_type!!,
                mealItems,
                mealId,
                childId,
                activityDate,
                notes
            )
        }.toEntity()
    }

    override suspend fun addNapActivity(
        activityType: String,
        additionalFieldNap: AdditionalFieldNap,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        return wrapApiResponse {
            apiService.addNapActivity(
                activityType,
                additionalFieldNap.nap_type!!,
                childId,
                activityDate,
                notes
            )
        }.toEntity()
    }

    override suspend fun addPottyActivity(
        activityType: String,
        additionalFieldPotty: AdditionalFieldPotty,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        return wrapApiResponse {
            apiService.addPottyActivity(
                activityType,
                additionalFieldPotty.waste_type!!,
                additionalFieldPotty.waste_shape!!,
                childId,
                activityDate,
                notes
            )
        }.toEntity()
    }

    override suspend fun addNoteMedIncidentsActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        return wrapApiResponse {
            apiService.addNoteMedIncidentActivity(
                activityType,
                childId,
                activityDate,
                notes
            )
        }.toEntity()
    }

    override suspend fun addAchievementActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        image: String?
    ): AddActivity {
        return wrapApiResponse {
            apiService.addAchievementActivity(
                activityType,
                childId,
                activityDate,
                notes,
                image!!
            )
        }.toEntity()
    }

    override suspend fun getProgress(): Custom {
        return wrapApiResponse {
            apiService.getProgress()
        }
    }

    override suspend fun getScales(): Custom {
        return wrapApiResponse {
            apiService.getScales()
        }
    }

    override suspend fun getCategories(): Custom {
        return wrapApiResponse {
            apiService.getCategories()
        }
    }

    override suspend fun getMileStones(): Custom {
        return wrapApiResponse {
            apiService.getMileStones()
        }
    }

    override suspend fun addCustomActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        categoryId: Int,
        progressId: Int,
        scaleId: Int
    ): AddActivity {
        return wrapApiResponse {
            apiService.addCustomActivity(
                activityType,
                childId,
                activityDate,
                notes, categoryId, progressId, scaleId
            )
        }.toEntity()
    }

    override suspend fun addObservationActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        mileStoneId: Int,
        staffOnly: Int
    ): AddActivity {
        return wrapApiResponse {
            apiService.addObservationActivity(
                activityType,
                childId,
                activityDate,
                notes, mileStoneId, staffOnly
            )
        }.toEntity()
    }

    override suspend fun addHealthCheckActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        temperature: String
    ): AddActivity {
        return wrapApiResponse {
            apiService.addHealthCheckActivity(
                activityType,
                childId,
                activityDate,
                notes,
                temperature
            )
        }.toEntity()
    }

    override suspend fun addNameToFaceActivity(
        activityType: String,
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        childId: Int,
        activityDate: String,
        notes: String,
        image: String
    ): AddActivity {
        return wrapApiResponse {
            apiService.addNameToFaceActivity(
                activityType,
                additionalFieldNameToFace.type!!,
                childId,
                activityDate,
                notes,
                image
            )
        }.toEntity()
    }


    private suspend fun <T> wrapApiResponse(
        request: suspend () -> Response<T>
    ): T {
        try {
            val response = request()
            if (response.isSuccessful) {
                return response.body() ?: throw EmptyDataException("No data")
            } else {
                throw when (response.code()) {
                    422 -> {
                        val jObjError = JSONObject(
                            response.errorBody()!!.string()
                        )
                        InvalidData(jObjError.getString("message"))
                    }

                    else -> ServerException("Server error")
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException("No Internet")
        } catch (io: IOException) {
            throw NoInternetException(io.message)
        } catch (e: SocketTimeoutException) {
            throw NoInternetException("Time out,No internet connection")
        }
    }
}