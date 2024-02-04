package com.gumibom.travelmaker.data.datasource.meeting

import com.gumibom.travelmaker.data.api.meeting.MeetingService
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import retrofit2.Response
import javax.inject.Inject

class MeetingRemoteDataSourceImpl @Inject constructor(
    private val meetingService : MeetingService
) : MeetingRemoteDataSource {
    override suspend fun getMarkerPositions(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): Response<MutableList<MarkerPositionResponseDTO>> {
        return meetingService.getMarkerPositions(latitude, longitude, radius)
    }

    override suspend fun getPostDetail(id: Long): Response<MeetingPostDTO> {
        return meetingService.getPostDetail(id)
    }


}