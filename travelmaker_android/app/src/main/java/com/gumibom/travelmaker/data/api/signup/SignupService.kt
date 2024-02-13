package com.gumibom.travelmaker.data.api.signup

import com.gumibom.travelmaker.data.dto.request.PhoneCertificationRequestDTO
import com.gumibom.travelmaker.data.dto.request.SignInUserDataRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.dto.response.SignInResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SignupService {

    // TODO 서버에서 보내주는 Response를 보고 구현하기
    // 서버에서 휴대폰 번호를 전송하면 인증번호를 받는 api
    @POST("/sms-certification/send")
    fun sendPhoneNumber(@Body phone : String) : Response<IsSuccessResponseDTO>

    // 이 아이디가 서버에 이미 저장되어 있는지(즉, 중복된 아이디인지) 체크하는 api
    // Boolean Value DTO 따로 만들어야 됨.
    @GET("/join/check/id-exists/{loginID}")
    suspend fun checkDuplicatedId(@Path("loginID") loginID : String) : Response<SignInResponseDTO>

    //아이디와 비밀번호를 입력했을 때 정상적인 유저인지 확인하고 로그인 성공 메시지 발송
    // 이 닉네임이 서버에 이미 저장되어 있는지(즉, 중복된 닉네임인지) 체크하는 api

    @GET("/join/check/nickname-exists/{nickname}")
    suspend fun checkDuplicatedNickName(@Path("nickname") nickname : String) : Response<SignInResponseDTO>

    @Multipart
    @POST("/join")
    suspend fun saveUserInfo(
        @Part image : MultipartBody.Part?,
        @Part("requestBody") requestBody : RequestBody
    ) : Response<IsSuccessResponseDTO>


//    @Multipart
//    @POST("/meeting-post/write")
//    suspend fun createMeeting(
//        @Part imgUrlMain : MultipartBody.Part,
//        @Part imgUrlSub : MultipartBody.Part?,
//        @Part imgUrlThr : MultipartBody.Part?,
//        @Part("requestDTO") meetingPostRequestDTO : RequestBody,
//    ) : Response<String>

    @POST("/sms-certification/confirm")
    fun isCertificationNumber(@Body phoneCertificationRequestDTO: PhoneCertificationRequestDTO) : Response<Boolean>

}