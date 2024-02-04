package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response




interface SignupRemoteDataSource {
    suspend fun sendPhoneNumber(phoneNumber : String) : Response<IsSuccessResponseDTO>
    suspend fun checkDuplicatedId(id:String): Response<IsSuccessResponseDTO>

    suspend fun checkDuplicateNickname(nickname:String):Response<IsSuccessResponseDTO>

    suspend fun saveUserData(userInfo: UserRequestDTO) :Response<IsSuccessResponseDTO> //회원가입 데이터 저장
}