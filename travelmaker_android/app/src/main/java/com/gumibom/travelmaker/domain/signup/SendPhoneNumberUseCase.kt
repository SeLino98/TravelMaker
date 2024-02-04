package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class SendPhoneNumberUseCase @Inject constructor(
    private val signupRepositoryImpl : SignupRepository
) {
    suspend fun sendPhoneNumber(phoneNumber : String) : IsSuccessResponseDTO? {
        val response = signupRepositoryImpl.sendPhoneNumber(phoneNumber)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}