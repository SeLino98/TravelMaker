package com.gumibom.travelmaker.data.datasource.meeting

import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import retrofit2.Response

interface MeetingRemoteDataSource {
    suspend fun getMarkerPositions(latitude : Double, longitude : Double, radius : Double) : Response<MutableList<MarkerPositionResponseDTO>>

    suspend fun getPostDetail(id:Long) : Response<MeetingPostDTO>



}