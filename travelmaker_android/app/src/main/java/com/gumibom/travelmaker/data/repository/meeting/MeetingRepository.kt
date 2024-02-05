package com.gumibom.travelmaker.data.repository.meeting

import com.gumibom.travelmaker.data.dto.request.MarkerCategoryPositionRequestDTO
import com.gumibom.travelmaker.data.dto.request.MarkerPositionRequestDTO
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import retrofit2.Response

interface MeetingRepository {

    suspend fun getPostDetail(id:Long):Response<MeetingPostDTO>

    suspend fun getMarkerPositions(markerPositionRequestDTO: MarkerPositionRequestDTO)
    : Response<MutableList<MarkerPositionResponseDTO>>
    suspend fun getMarkerCategoryPositions(markerCategoryPositionRequestDTO: MarkerCategoryPositionRequestDTO) : Response<MutableList<MarkerPositionResponseDTO>>
}