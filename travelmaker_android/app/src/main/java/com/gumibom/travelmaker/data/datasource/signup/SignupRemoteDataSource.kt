package com.gumibom.travelmaker.data.datasource.signup

import retrofit2.Response


interface SignupRemoteDataSource {
    suspend fun sendPhoneNumber(phoneNumber : String) : Response<Boolean>
}