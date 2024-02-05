package com.gumibom.travelmaker.data.dto.request

data class MarkerCategoryPositionRequestDTO(
    val latitude : Double,
    val longitude : Double,
    val radius : Int,
    val categories : List<String>
)
