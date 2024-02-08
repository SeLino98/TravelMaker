package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class CheckCertificationUseCase @Inject constructor(
    private val signupRepositoryImpl: SignupRepository
) {
    suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO) {
        signupRepositoryImpl.isCertificationNumber(phoneCertificationRequestDTO)
    }

}