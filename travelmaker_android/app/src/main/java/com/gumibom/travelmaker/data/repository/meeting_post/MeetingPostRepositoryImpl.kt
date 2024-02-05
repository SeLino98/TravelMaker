package com.gumibom.travelmaker.data.repository.meeting_post

import com.gumibom.travelmaker.data.datasource.meeting_post.MeetingPostRemoteDataSource
import com.gumibom.travelmaker.data.datasource.meeting_post.MeetingPostRemoteDataSourceImpl
import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import retrofit2.Response
import javax.inject.Inject

class MeetingPostRepositoryImpl @Inject constructor(
    private val meetingPostRemoteDataSourceImpl: MeetingPostRemoteDataSource
) : MeetingPostRepository {
    // 모임 생성 api
    override suspend fun createMeeting(meetingPostRequestDTO: MeetingPostRequestDTO): Response<String> {
        return meetingPostRemoteDataSourceImpl.createMeeting(meetingPostRequestDTO)
    }
}