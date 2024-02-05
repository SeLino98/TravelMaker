package com.gumibom.travelmaker.data.api.meeting_post

import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MeetingPostService {
    @POST("/meeting-post/write")
    suspend fun createMeeting(@Body meetingPostRequestDTO : MeetingPostRequestDTO) : Response<String>
}