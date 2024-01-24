package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.api.signup.SignupService
import retrofit2.Response
import javax.inject.Inject

class SignupRemoteDataSourceImpl @Inject constructor(
    private val signupService : SignupService
): SignupRemoteDataSource {
    override suspend fun sendPhoneNumber(phoneNumber: String): Response<Boolean> {
        return signupService.sendPhoneNumber(phoneNumber)
    }
}