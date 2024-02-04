package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl
import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO

import retrofit2.Response
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val signupRemoteDataSourceImpl: SignupRemoteDataSource
) : SignupRepository {


    override suspend fun sendPhoneNumber(phoneNumber: String): Response<IsSuccessResponseDTO> {
        return signupRemoteDataSourceImpl.sendPhoneNumber(phoneNumber)
    }
    override suspend fun checkDuplicatedId(id: String): Response<IsSuccessResponseDTO> {


        return signupRemoteDataSourceImpl.checkDuplicatedId(id)
    }


    override suspend fun checkDuplicateNickname(nickname: String): Response<IsSuccessResponseDTO> {
        return signupRemoteDataSourceImpl.checkDuplicateNickname(nickname)
    }

    override suspend fun saveUserData(userInfo: UserRequestDTO): Response<IsSuccessResponseDTO> {
        return signupRemoteDataSourceImpl.saveUserData(userInfo)
    }
}