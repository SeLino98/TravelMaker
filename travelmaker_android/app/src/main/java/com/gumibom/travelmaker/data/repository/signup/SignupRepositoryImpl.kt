package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSource: SignupRemoteDataSource
) : SignupRepository {


    override suspend fun sendPhoneNumber(phoneNumber: String): Response<Boolean> {
        return signupRemoteDataSource.sendPhoneNumber(phoneNumber)
    }
    override suspend fun checkDuplicatedId(id: String): Response<Boolean> {
        val response = signupRemoteDataSource.checkDuplicatedId(id)
        return response

    }
}