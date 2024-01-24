package com.gumibom.travelmaker.data.api.signup

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignupService {

    // TODO 쿼리 파라미터 API 나올 시에 수정 필요
    // 서버에서 휴대폰 번호를 전송하면 인증번호를 받는 api
    @POST("/users/join/auth")
    fun sendPhoneNumber(@Query("phoneNum") phoneNumber : String) : Response<String>

    @GET("/users/join/auth")
    fun isCertificationNumber(@Query("number") secretNumber : String) : Response<Boolean>

    @POST("/users/join/fill/check-id-dup")
    fun checkDuplicatedId(@Body id : String) : Response<Boolean>
}