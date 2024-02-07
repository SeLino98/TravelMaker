package com.gumibom.travelmaker.model

data class User(
    val userId : Long,
    val loginId : String,
    val password : String,
    val nickname : String,
    val imgUrl : String
) {
    constructor() : this(0, "", "", "","")
    constructor(loginId: String, password: String) : this(0, loginId, password, "", "")

}
