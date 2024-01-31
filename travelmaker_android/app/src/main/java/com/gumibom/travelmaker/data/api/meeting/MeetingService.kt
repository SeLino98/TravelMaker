package com.gumibom.travelmaker.data.api.meeting

import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MeetingService {
    @GET("/meeting-post")
    suspend fun getMarkerPositions(
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude  : Double,
        @Query("radius") radius : Double
    ) : Response<MutableList<MarkerPositionResponseDTO>>
}