package com.gumibom.travelmaker.model

import java.io.Serializable

data class GoogleUser(
    val uId : String,
    val email : String
) : Serializable
