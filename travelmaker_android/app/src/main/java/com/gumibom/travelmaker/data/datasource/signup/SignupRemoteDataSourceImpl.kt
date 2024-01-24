package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.api.signup.SignupService
import javax.inject.Inject

class SignupRemoteDataSourceImpl @Inject constructor(
    private val signupService : SignupService
): SignupRemoteDataSource {
}