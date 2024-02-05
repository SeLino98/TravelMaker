package com.gumibom.travelmaker.domain.meeting_post

import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import com.gumibom.travelmaker.data.repository.meeting_post.MeetingPostRepository
import com.gumibom.travelmaker.data.repository.meeting_post.MeetingPostRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class PostMeetingUseCase @Inject constructor(
    private val meetingPostRepositoryImpl: MeetingPostRepository
) {

    suspend fun createMeeting() {

    }
}