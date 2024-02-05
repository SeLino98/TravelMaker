package com.gumibom.travelmaker.data.datasource.meeting_post

import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import retrofit2.Response


interface MeetingPostRemoteDataSource {

    suspend fun createMeeting(meetingPostRequestDTO : MeetingPostRequestDTO) : Response<String>
}