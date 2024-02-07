package com.gumibom.travelmaker.data.api.meeting

import com.gumibom.travelmaker.data.dto.request.MarkerCategoryPositionRequestDTO
import com.gumibom.travelmaker.data.dto.request.MarkerPositionRequestDTO
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetingService {
    @POST("/meeting-post")
    suspend fun getMarkerPositions(
        @Body markerPositionRequestDTO: MarkerPositionRequestDTO
    ) : Response<MutableList<MarkerPositionResponseDTO>>

    @POST("/meeting-post")
    suspend fun getMarkerCategoryPositions(
        @Body markerCategoryPositionRequestDTO: MarkerCategoryPositionRequestDTO
    ) : Response<MutableList<MarkerPositionResponseDTO>>

    @GET("/meeting-post/{meetingPostId}")
    suspend fun getPostDetail(
        @Path("meetingPostId") id: Long
    ): Response<MeetingPostDTO>



}