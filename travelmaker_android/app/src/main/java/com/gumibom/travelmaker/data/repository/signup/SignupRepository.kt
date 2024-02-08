package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

interface SignupRepository {


    suspend fun sendPhoneNumber(phoneNumber : String) : Response<IsSuccessResponseDTO>
    suspend fun checkDuplicatedId(id:String): Response<IsSuccessResponseDTO>

    suspend fun checkDuplicateNickname(nickname:String) :Response<IsSuccessResponseDTO> //닉네임 중복 체크

    suspend fun saveUserData(userInfo: UserRequestDTO) :Response<IsSuccessResponseDTO> //회원가입 데이터 저장

    suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO) : Response<Boolean>
}