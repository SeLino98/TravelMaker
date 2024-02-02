package com.gumibom.travelmaker.data.repository.meeting

import com.gumibom.travelmaker.data.datasource.meeting.MeetingRemoteDataSource
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import com.gumibom.travelmaker.data.repository.login.LoginRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class MeetingRepositoryImpl @Inject constructor(
    private val meetingRemoteDataSourceImpl: MeetingRemoteDataSource
): MeetingRepository {
    override suspend fun getPostDetail(id: Int): Response<MeetingPostDTO> {
        return meetingRemoteDataSourceImpl.getPostDetail(id)
    }

    override suspend fun getMarkerPositions(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): Response<MutableList<MarkerPositionResponseDTO>> {
        return meetingRemoteDataSourceImpl.getMarkerPositions(latitude, longitude, radius)
    }



}