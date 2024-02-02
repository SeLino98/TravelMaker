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
        return signupService.checkDuplicatedId(id)
    }

    override suspend fun saveUserData(userInfo: UserRequestDTO): Response<Boolean> {
        return signupService.saveUserInfo(userInfo)
    }

    override suspend fun checkDuplicatedNickname(nickname: String): Response<Boolean> {
        return signupService.checkDuplicatedNickname(nickname)
    }
}