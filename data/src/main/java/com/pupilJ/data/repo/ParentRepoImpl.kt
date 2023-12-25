package com.pupilJ.data.repo

import com.pupilJ.data.mapper.childrenActivityDtoToEntity
import com.pupilJ.data.mapper.childrenDtoOtEntity
import com.pupilJ.data.mapper.toEntity
import com.pupilJ.data.remote.ApiService
import com.pupilJ.domain.models.AboutUS
import com.pupilJ.domain.models.Children
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.models.ServerException
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.models.Teacher
import com.pupilJ.domain.repo.parentrepo.ParentRepo
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class ParentRepoImpl(private val apiService: ApiService) : ParentRepo {

    override suspend fun loginParent(nationalId: String, password: String) =
        wrapApiResponse {
            apiService.loginParent(nationalId, password)
        }.toEntity()


    override suspend fun getChildren(): List<Children> {
        return childrenDtoOtEntity(wrapApiResponse {
            apiService.getChildren()
        })
    }

    override suspend fun getTeacherOfChildren(childId: Int): List<Teacher> {
        return wrapApiResponse {
            apiService.getTeachersOfChildren(childId)
        }.toEntity()
    }

    override suspend fun getChildrenActivity(id: Int): List<ChildrenActivity> {
        return childrenActivityDtoToEntity(wrapApiResponse {
            apiService.getActivityOfChildren(id)
        })
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

    override suspend fun aboutUs(schoolId: Int): AboutUS {
        return wrapApiResponse {
            apiService.aboutUs(schoolId)
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
                422 -> InvalidData("Invalid username or password")
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
