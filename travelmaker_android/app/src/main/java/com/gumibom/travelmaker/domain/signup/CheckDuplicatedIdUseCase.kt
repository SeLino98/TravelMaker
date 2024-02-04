package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import javax.inject.Inject

class CheckDuplicatedIdUseCase @Inject constructor(
    private val repository: SignupRepository
) {
    suspend fun checkDuplicatedId(id:String): IsSuccessResponseDTO?{
        val response = repository.checkDuplicatedId(id)

        if (response.isSuccessful) {
            val body = response.body()
            return body
        } else {
            return null
        }
    }
}