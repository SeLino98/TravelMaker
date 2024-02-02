package com.gumibom.travelmaker.data.datasource.signup

import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import retrofit2.Response




interface SignupRemoteDataSource {
    suspend fun sendPhoneNumber(phoneNumber : String) : Response<Boolean>
    suspend fun checkDuplicatedId(id:String): Response<Boolean>

    suspend fun checkDuplicateNickname(nickname:String):Response<Boolean>

    suspend fun saveUserData(userInfo: UserRequestDTO) :Response<Boolean> //회원가입 데이터 저장
}