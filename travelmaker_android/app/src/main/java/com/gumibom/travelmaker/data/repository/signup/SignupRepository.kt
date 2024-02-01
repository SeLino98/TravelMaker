package com.gumibom.travelmaker.data.repository.signup

import retrofit2.Response

interface SignupRepository {

    suspend fun sendPhoneNumber(phoneNumber : String) : Response<Boolean>
    suspend fun checkDuplicatedId(id:String): Response<Boolean>
    suspend fun checkDuplicatedNickname(nickname:String): Response<Boolean>

}