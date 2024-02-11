package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.api.signup.SignupService
import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.dto.response.SignInResponseDTO
import retrofit2.Response
import javax.inject.Inject

class SignupRemoteDataSourceImpl @Inject constructor(
    private val signupService : SignupService
): SignupRemoteDataSource {

    override suspend fun sendPhoneNumber(phoneNumber: String): Response<IsSuccessResponseDTO> {
        return signupService.sendPhoneNumber(phoneNumber)
    }

    override suspend fun checkDuplicatedId(id: String): Response<SignInResponseDTO> {
        return signupService.checkDuplicatedId(id)
    }

    override suspend fun checkDuplicateNickname(nickname: String): Response<SignInResponseDTO> {
       return signupService.checkDuplicatedNickName(nickname)

    }

    override suspend fun saveUserData(userInfo: UserRequestDTO): Response<IsSuccessResponseDTO> {
        return signupService.saveUserInfo(userInfo)
    }

    override suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO): Response<Boolean> {
        return signupService.isCertificationNumber(phoneCertificationRequestDTO)
    }
}