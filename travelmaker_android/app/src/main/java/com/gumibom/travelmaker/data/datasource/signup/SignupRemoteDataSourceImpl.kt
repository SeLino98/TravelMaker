package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.api.signup.SignupService
import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import retrofit2.Response
import javax.inject.Inject

class SignupRemoteDataSourceImpl @Inject constructor(
    private val signupService : SignupService
): SignupRemoteDataSource {

    override suspend fun sendPhoneNumber(phoneNumber: String): Response<Boolean> {
        return signupService.sendPhoneNumber(phoneNumber)
    }
    override suspend fun checkDuplicatedId(id: String): Response<Boolean> {
        val response = signupService.checkDuplicatedId(id)
        return response

    }

    override suspend fun checkDuplicateNickname(nickname: String): Response<Boolean> {
        
    }

    override suspend fun saveUserData(userInfo: UserRequestDTO): Response<Boolean> {

    }

    private fun mapper(){

    }
}