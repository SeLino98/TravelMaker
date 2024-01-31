package com.gumibom.travelmaker.data.datasource.meeting

import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import retrofit2.Response

interface MeetingRemoteDataSource {
    suspend fun getMarkerPositions(latitude : Double, longitude : Double, radius : Double) : Response<MutableList<MarkerPositionResponseDTO>>
}