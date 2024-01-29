package com.gumibom.travelmaker.data.api.login

import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/users/login")
    suspend fun login(@Body loginRequestDTO: LoginRequestDTO) : Response<IsSuccessResponseDTO>
}