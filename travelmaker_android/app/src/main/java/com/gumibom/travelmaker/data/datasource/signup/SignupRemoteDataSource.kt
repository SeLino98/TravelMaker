package com.gumibom.travelmaker.data.datasource.signup

import android.provider.ContactsContract.CommonDataKinds.Nickname
import retrofit2.Response

interface SignupRemoteDataSource {
    suspend fun sendPhoneNumber(phoneNumber : String) : Response<Boolean>
    suspend fun checkDuplicatedId(id:String): Response<Boolean>
    suspend fun checkDuplicatedNickname(nickname: String): Response<Boolean>
}