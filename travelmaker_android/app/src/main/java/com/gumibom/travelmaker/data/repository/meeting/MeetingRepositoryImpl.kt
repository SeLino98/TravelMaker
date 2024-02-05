package com.gumibom.travelmaker.data.repository.meeting

import com.gumibom.travelmaker.data.datasource.meeting.MeetingRemoteDataSource
import com.gumibom.travelmaker.data.dto.request.MarkerCategoryPositionRequestDTO
import com.gumibom.travelmaker.data.dto.request.MarkerPositionRequestDTO
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import com.gumibom.travelmaker.data.repository.login.LoginRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class MeetingRepositoryImpl @Inject constructor(
    private val meetingRemoteDataSourceImpl: MeetingRemoteDataSource
): MeetingRepository {
    override suspend fun getPostDetail(id: Long): Response<MeetingPostDTO> {
        return meetingRemoteDataSourceImpl.getPostDetail(id)
    }

    override suspend fun getMarkerPositions(markerPositionRequestDTO: MarkerPositionRequestDTO): Response<MutableList<MarkerPositionResponseDTO>> {
        return meetingRemoteDataSourceImpl.getMarkerPositions(markerPositionRequestDTO)
    }

    override suspend fun getMarkerCategoryPositions(markerCategoryPositionRequestDTO: MarkerCategoryPositionRequestDTO): Response<MutableList<MarkerPositionResponseDTO>> {
        return meetingRemoteDataSourceImpl.getMarkerCategoryPositions(markerCategoryPositionRequestDTO)
    }
}