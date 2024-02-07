package com.gumibom.travelmaker.model

data class User(
    val userId : Long,
    val accessToken : String,
    val loginId : String,
    val password : String,
    val nickname : String,
    val imgUrl : String
) {
    constructor() : this(0, "","", "", "","")
    constructor(loginId: String, password: String, accessToken : String) : this(0, accessToken,loginId, password, "", "")

}
