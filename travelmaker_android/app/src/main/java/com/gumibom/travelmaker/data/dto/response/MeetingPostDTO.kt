package com.gumibom.travelmaker.data.dto.response

data class MeetingPostDTO(
    val authDate: String,
    val categories: List<String>,
    val content: String,
    val deadline: String,
    val endDate: String,
    val imgUrlMain: String,
    val imgUrlSub: String,
    val imgUrlThr: String,
    val memberMax: Int,
    val nativeMin: Int,
    val position: Position,
    val startDate: String,
    val title: String,
    val travelerMin: Int,
    val username: String
)