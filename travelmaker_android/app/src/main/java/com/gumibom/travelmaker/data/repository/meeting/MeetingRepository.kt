package com.gumibom.travelmaker.data.repository.meeting

import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import retrofit2.Response

interface MeetingRepository {
    suspend fun getMarkerPositions(latitude : Double, longitude : Double, radius : Double) : Response<MutableList<MarkerPositionResponseDTO>>
}