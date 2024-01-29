package com.gumibom.travelmaker.data.repository.login

import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSource
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSourceImpl
import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSourceImpl: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(loginRequestDTO: LoginRequestDTO): Response<IsSuccessResponseDTO> {
        return loginRemoteDataSourceImpl.login(loginRequestDTO)
    }
}