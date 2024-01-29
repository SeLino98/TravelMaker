package com.gumibom.travelmaker.data.repository.login

import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

interface LoginRepository {
    suspend fun login(loginRequestDTO: LoginRequestDTO) : Response<IsSuccessResponseDTO>
}