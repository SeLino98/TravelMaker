package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl
import retrofit2.Response
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSourceImpl: SignupRemoteDataSource
) : SignupRepository {
    override suspend fun checkDuplicatedId(id: String): Response<Boolean> {
        val response = signupRemoteDataSourceImpl.checkDuplicatedId(id)
        return response
    }
}