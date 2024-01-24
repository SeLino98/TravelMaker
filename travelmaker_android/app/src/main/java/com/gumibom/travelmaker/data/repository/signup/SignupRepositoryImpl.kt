package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSourceImpl: SignupRemoteDataSource
) : SignupRepository {
}