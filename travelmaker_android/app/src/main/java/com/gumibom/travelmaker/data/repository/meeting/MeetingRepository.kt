package com.gumibom.travelmaker.data.repository.meeting

import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import retrofit2.Response

interface MeetingRepository {

    suspend fun getPostDetail(id:Int):Response<MeetingPostDTO>

    suspend fun getMarkerPositions(latitude : Double
                                   , longitude : Double, radius : Double)
    : Response<MutableList<MarkerPositionResponseDTO>>





}