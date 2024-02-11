package com.gumibom.travelmaker.data.repository.signup

import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.request.SignInUserDataRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.dto.response.SignInResponseDTO
import retrofit2.Response

interface SignupRepository {


    suspend fun sendPhoneNumber(phoneNumber : String) : Response<IsSuccessResponseDTO>
    suspend fun checkDuplicatedId(id:String): Response<SignInResponseDTO>

    suspend fun checkDuplicateNickname(nickname:String) :Response<SignInResponseDTO> //닉네임 중복 체크

    suspend fun saveUserData(userInfo: SignInUserDataRequestDTO) :Response<IsSuccessResponseDTO> //회원가입 데이터 저장

    suspend fun isCertificationNumber(phoneCertificationRequestDTO: PhoneCertificationRequestDTO) : Response<Boolean>
}