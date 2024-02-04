package com.gumibom.travelmaker.data.dto.response

data class MeetingPostDTO(
    val authDate: String? = null,
    val categories: List<String>? = emptyList(),
    val content: String? = null,
    val deadline: String? = null,
    val endDate: String? = null,
    val imgUrlMain: String? = null,
    val imgUrlSub: String? = null,
    val imgUrlThr: String? = null,
    val memberMax: Int? = null,
    val nativeMin: Int? = null,
    val position: Position? = null, // Position도 기본값이 필요하며, 해당 클래스에도 기본값을 설정해야 합니다.
    val startDate: String? = null,
    val title: String? = null,
    val travelerMin: Int? = null,
    val username: String? = null
)
