package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class CheckCertificationUseCase @Inject constructor(
    private val signupRepositoryImpl: SignupRepository
) {
    suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO) : IsSuccessResponseDTO?{
        val response = signupRepositoryImpl.isCertificationNumber(phoneCertificationRequestDTO)

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                return body
            }
        }
        return null
    }

}