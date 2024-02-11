package com.gumibom.travelmaker.model

data class RequestUserData(
    var birth: String,
    var categories: List<String>,
    var email: String,
    var gender: String,
    var nation: String,
    var nickname: String,
    var password: String,
    var phone: String,
    var town: String,
    var username: String
)