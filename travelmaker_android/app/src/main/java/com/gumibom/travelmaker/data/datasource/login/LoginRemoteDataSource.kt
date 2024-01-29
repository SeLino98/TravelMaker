package com.gumibom.travelmaker.data.datasource.login

import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

interface LoginRemoteDataSource {
    suspend fun login(loginRequestDTO: LoginRequestDTO) : Response<IsSuccessResponseDTO>
}