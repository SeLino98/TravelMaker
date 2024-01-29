package com.gumibom.travelmaker.domain.login

import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.repository.login.LoginRepository
import com.gumibom.travelmaker.data.repository.login.LoginRepositoryImpl
import com.gumibom.travelmaker.model.BooleanResponse
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepositoryImpl: LoginRepository
){
    suspend fun login(loginRequestDTO: LoginRequestDTO) : BooleanResponse {
        val response = loginRepositoryImpl.login(loginRequestDTO)
        var booleanResponse = BooleanResponse(false, "")

        if (response.isSuccessful ) {
            val responseBody = response.body()

            if (responseBody != null) {
                booleanResponse = BooleanResponse(
                    responseBody.isSuccess,
                    responseBody.message
                )
            }
        }
        return booleanResponse
    }

}