package com.example.data.repo

import com.example.data.mapper.childrenDtoOtEntity
import com.example.data.mapper.toEntity
import com.example.data.remote.ApiService
import com.example.domain.models.Children
import com.example.domain.models.EmptyDataException
import com.example.domain.models.NoInternetException
import com.example.domain.models.ServerException
import com.example.domain.models.User
import com.example.domain.models.UserProfile
import com.example.domain.models.WrongPassOrEmailException
import com.example.domain.repo.parentrepo.ParentRepo
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

//class ParentRepoImpl(private val remoteDataSource: IRemoteDataSource) : ParentRepo {
//    override suspend fun login(nationalId: String, password: String) =
//        remoteDataSource.login(nationalId, password).toEntity()
//
////    override suspend fun getProfile(token:String): ProfileModel =
////        remoteDataSource.getProfile(token)
//}
class ParentRepoImpl(private val apiService: ApiService) : ParentRepo {

    override suspend fun loginParent(nationalId: String, password: String) =
        wrapApiResponse {
            apiService.loginParent(nationalId, password)
        }.toEntity()


//    override suspend fun getProfile(token: String): UserProfile =
//        wrapApiResponse {
//            apiService.getProfile()
//        }.toEntity()

    override suspend fun getChildren(): List<Children> {
        return childrenDtoOtEntity(wrapApiResponse {
            apiService.getChildren()
        })
    }


    //    override suspend fun getProfile(token:String): ProfileModel =
//        remoteDataSource.getProfile(token)
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
