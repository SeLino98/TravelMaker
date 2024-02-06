package com.gumibom.travelmaker.data.repository.firebase

import com.gumibom.travelmaker.data.dto.request.FcmRequestGroupDTO
import com.gumibom.travelmaker.data.dto.request.FcmTokenRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

interface FirebaseFcmRepository {
    suspend fun uploadToken(
        firebaseFcmTokenRequestDTO: FcmTokenRequestDTO
    ):Response<IsSuccessResponseDTO>

    suspend fun groupRequest(
        fcmRequestGroup: FcmRequestGroupDTO
    ):Response<IsSuccessResponseDTO>

    suspend fun acceptCrew(
        fcmRequestGroup: FcmRequestGroupDTO
    ):Response<IsSuccessResponseDTO>
}