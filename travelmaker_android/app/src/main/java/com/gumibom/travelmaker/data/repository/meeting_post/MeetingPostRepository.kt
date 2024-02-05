package com.gumibom.travelmaker.data.repository.meeting_post

import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import retrofit2.Response

interface MeetingPostRepository {

    suspend fun createMeeting(meetingPostRequestDTO : MeetingPostRequestDTO) : Response<String>
}