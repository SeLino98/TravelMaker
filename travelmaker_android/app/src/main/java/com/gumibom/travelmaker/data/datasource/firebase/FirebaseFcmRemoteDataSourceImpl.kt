package com.gumibom.travelmaker.data.datasource.firebase

import com.gumibom.travelmaker.data.api.firebase.FirebaseTokenService
import com.gumibom.travelmaker.data.dto.request.FcmTokenRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response
import javax.inject.Inject

class FirebaseFcmRemoteDataSourceImpl @Inject constructor(
    private val firebaseTokenService: FirebaseTokenService
):FirebaseFcmRemoteDataSource{

    override suspend fun uploadToken(fcmTokenRequestDTO: FcmTokenRequestDTO): Response<IsSuccessResponseDTO> {
        return firebaseTokenService.uploadToken(fcmTokenRequestDTO)
    }


}