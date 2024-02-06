package com.gumibom.travelmaker.data.datasource.firebase

import com.gumibom.travelmaker.data.dto.request.FcmRequestGroupDTO
import com.gumibom.travelmaker.data.dto.request.FcmTokenRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

interface FirebaseFcmRemoteDataSource {
    suspend fun uploadToken(
        fcmTokenRequestDTO: FcmTokenRequestDTO
    ):Response<IsSuccessResponseDTO>

    suspend fun groupRequest(
        fcmRequestGroup: FcmRequestGroupDTO
    ) : Response<IsSuccessResponseDTO>

    suspend fun acceptCrew(
        fcmRequestGroup: FcmRequestGroupDTO
    ):Response<IsSuccessResponseDTO>

}