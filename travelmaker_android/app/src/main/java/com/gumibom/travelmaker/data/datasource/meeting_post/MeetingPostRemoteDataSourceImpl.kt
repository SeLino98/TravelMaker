package com.gumibom.travelmaker.data.datasource.meeting_post

import com.gumibom.travelmaker.data.api.meeting_post.MeetingPostService
import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import retrofit2.Response
import javax.inject.Inject

class MeetingPostRemoteDataSourceImpl @Inject constructor(
    private val meetingPostService : MeetingPostService
) : MeetingPostRemoteDataSource {

    override suspend fun createMeeting(meetingPostRequestDTO: MeetingPostRequestDTO): Response<String> {
        return meetingPostService.createMeeting(meetingPostRequestDTO)
    }
}