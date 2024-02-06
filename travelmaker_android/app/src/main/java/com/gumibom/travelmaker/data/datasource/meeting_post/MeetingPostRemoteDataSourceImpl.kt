package com.gumibom.travelmaker.data.datasource.meeting_post

import com.gumibom.travelmaker.data.api.meeting_post.MeetingPostService
import com.gumibom.travelmaker.data.dto.request.MeetingPostRequestDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class MeetingPostRemoteDataSourceImpl @Inject constructor(
    private val meetingPostService : MeetingPostService
) : MeetingPostRemoteDataSource {

    override suspend fun createMeeting(
        token : String,
        imgUrlMain: MultipartBody.Part,
        imgUrlSub: MultipartBody.Part?,
        imgUrlThr: MultipartBody.Part?,
        meetingPostRequestDTO: RequestBody
    ): Response<String> {
        return meetingPostService.createMeeting(
            token, imgUrlMain, imgUrlSub, imgUrlThr, meetingPostRequestDTO,
        )
    }
}