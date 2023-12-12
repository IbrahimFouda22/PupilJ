package com.example.data.repo

import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import com.example.data.mapper.childrenActivityDtoToEntity
import com.example.data.mapper.childrenTeacherDtoToEntity
import com.example.data.mapper.classDtoToEntity
import com.example.data.mapper.toEntity
import com.example.data.remote.ApiService
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
import com.example.domain.models.EmptyDataException
import com.example.domain.models.MealType
import com.example.domain.models.NoInternetException
import com.example.domain.models.PhotoVideo
import com.example.domain.models.ServerException
import com.example.domain.models.WrongPassOrEmailException
import com.example.domain.repo.teacherrepo.TeacherRepo
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import java.io.IOException
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

    override suspend fun uploadImage(file: File, activityType: String, childI: String): PhotoVideo {
        val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
        val photo = MultipartBody.Part.createFormData("photo", file.name, requestFile)
        val activityName = RequestBody.create(MediaType.parse("text/plain"), activityType)
        val childId = RequestBody.create(MediaType.parse("text/plain"), childI)
        //Log.d("photo", file.path.toString() + photo.toString() + activityName.toString() + childId.toString())
        return wrapApiResponse {
            apiService.uploadPhoto(activityName, childId, photo)
        }.toEntity()
    }

    override suspend fun uploadVideo(file: File, activityType: String, childId: String): PhotoVideo {
        //val file = File(uri.path!!)
        val requestFile = RequestBody.create(MediaType.parse("video/*"), file)
        val video = MultipartBody.Part.createFormData("video", file.name, requestFile)
        val activityName = RequestBody.create(MediaType.parse("text/plain"), activityType)
        val child = RequestBody.create(MediaType.parse("text/plain"), childId)
        return wrapApiResponse {
            apiService.uploadVideo(activityName,video,child)
        }.toEntity()
    }

    override suspend fun getChildrenActivity(id: Int): List<ChildrenActivity> {
        return childrenActivityDtoToEntity(
            wrapApiResponse {
                apiService.getActivityOfChildren(id)
            }
        )
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

    override suspend fun getMealItem(mealTypeId: Int): List<MealType> {
        return wrapApiResponse {
            apiService.getMealItems (mealTypeId)
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
                    422 -> WrongPassOrEmailException("Invalid username or password")
                    else -> ServerException("Server error")
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException("no Internet")
        } catch (io: IOException) {
            throw NoInternetException(io.message)
        }
    }
}