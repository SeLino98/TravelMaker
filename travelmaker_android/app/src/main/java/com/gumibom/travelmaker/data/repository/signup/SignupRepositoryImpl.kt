package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl
import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.request.SignInUserDataRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.dto.response.SignInResponseDTO

import retrofit2.Response
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSourceImpl: SignupRemoteDataSource
) : SignupRepository {

    override suspend fun sendPhoneNumber(phoneNumber: String): Response<IsSuccessResponseDTO> {
        return signupRemoteDataSourceImpl.sendPhoneNumber(phoneNumber)
    }
    override suspend fun checkDuplicatedId(id: String): Response<SignInResponseDTO> {
        return signupRemoteDataSourceImpl.checkDuplicatedId(id)
    }


    override suspend fun checkDuplicateNickname(nickname: String): Response<SignInResponseDTO> {
        return signupRemoteDataSourceImpl.checkDuplicateNickname(nickname)
    }

    override suspend fun saveUserData(userInfo: SignInUserDataRequestDTO): Response<IsSuccessResponseDTO> {
        return signupRemoteDataSourceImpl.saveUserData(userInfo)
    }

    override suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO): Response<Boolean> {
        return signupRemoteDataSourceImpl.isCertificationNumber(phoneCertificationRequestDTO)
    }


}