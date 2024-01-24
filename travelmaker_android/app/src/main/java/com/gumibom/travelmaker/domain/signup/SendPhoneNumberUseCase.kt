package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class SendPhoneNumberUseCase @Inject constructor(
    private val signupRepositoryImpl : SignupRepository
) {
}