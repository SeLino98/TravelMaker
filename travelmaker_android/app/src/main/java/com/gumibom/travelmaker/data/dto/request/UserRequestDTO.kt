package com.gumibom.travelmaker.data.dto.request

data class UserRequestDTO(
    //id 생략
    val category: Int,
    val userId :String,
    val password : String,
    val email:String,
    val nickname : String,
    val gender : Boolean,
    val birth:String,
    val phoneNum :String,
    val imgURL : String,
    val town : String,
    val belief: Double
    //회원가입 유저 데이터 저장시
    //생성 일자와 최초 로그인 일시도 저장해야 됨.

)