package com.gumibom.travelmaker.data.api.signup

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignupService {

    // TODO 서버에서 보내주는 Response를 보고 구현하기
    // 서버에서 휴대폰 번호를 전송하면 인증번호를 받는 api
    @POST("/users/join/auth")
    fun sendPhoneNumber(@Body phoneNumber : String) : Response<Boolean>

    // 이 인증번호가 맞는지 확인하는 api
    @GET("/users/join/auth")
    fun isCertificationNumber(@Query("number") secretNumber : String) : Response<Boolean>
    // 이 아이디가 서버에 이미 저장되어 있는지(즉, 중복된 아이디인지) 체크하는 api
    @POST("/users/join/fill/check-id-dup")
    fun checkDuplicatedId(@Body id : String) : Response<Boolean>
    // 이 닉네임이 서버에 이미 저장되어 있는지(즉, 중복된 닉네임인지) 체크하는 api
    @POST("/users/join/fill/check-nick-dup")
    fun checkDuplicatedNickname(@Body nickname: String) : Response<Boolean>
}