package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class SendPhoneNumberUseCase @Inject constructor(
    private val signupRepositoryImpl : SignupRepository
) {
    suspend fun sendPhoneNumber(phoneNumber : String) : Boolean? {
        val response = signupRepositoryImpl.sendPhoneNumber(phoneNumber)

        if (response.isSuccessful) {
            return response.body() ?: false
        }
        return null
    }
}