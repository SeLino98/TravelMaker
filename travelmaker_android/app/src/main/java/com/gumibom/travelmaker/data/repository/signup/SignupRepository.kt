package com.gumibom.travelmaker.data.repository.signup

import retrofit2.Response

interface SignupRepository {
    suspend fun checkDuplicatedId(id:String): Response<Boolean>

}