package com.gumibom.travelmaker.data.dto.response

data class Position(
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val town: String
) {
    constructor() : this(0.0, 0.0, "", "")
}