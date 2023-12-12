package com.example.data.remote

import com.example.data.dto.LoginDto
import com.example.domain.models.EmptyDataException
import com.example.domain.models.NoInternetException
import com.example.domain.models.ServerException
import com.example.domain.models.WrongPassOrEmailException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class RemoteDataSource (private val apiService: ApiService) : IRemoteDataSource {
    override suspend fun login(nationalId: String, password: String): LoginDto =
        wrapApiResponse {
            apiService.loginParent(nationalId, password)
        }

//    override suspend fun getProfile(token: String): ProfileModel = apiService.getProfile(token)
//
//    override suspend fun getChildren(nationalId: String, password: String): LoginDto =
//        apiService.getChildren(nationalId, password)

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