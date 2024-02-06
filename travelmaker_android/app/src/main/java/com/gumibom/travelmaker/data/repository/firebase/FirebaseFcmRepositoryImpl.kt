package com.gumibom.travelmaker.data.repository.firebase

import com.gumibom.travelmaker.data.datasource.firebase.FirebaseFcmRemoteDataSource
import com.gumibom.travelmaker.data.dto.request.FcmRequestGroupDTO
import com.gumibom.travelmaker.data.dto.request.FcmTokenRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import retrofit2.Response

class FirebaseFcmRepositoryImpl (
        private val firebaseFcmRemoteDataSource: FirebaseFcmRemoteDataSource
): FirebaseFcmRepository{


    override suspend fun uploadToken(firebaseFcmTokenRequestDTO: FcmTokenRequestDTO): Response<IsSuccessResponseDTO> {
        return firebaseFcmRemoteDataSource.uploadToken(firebaseFcmTokenRequestDTO)
    }

    override suspend fun groupRequest(fcmRequestGroup: FcmRequestGroupDTO): Response<IsSuccessResponseDTO> {
        return firebaseFcmRemoteDataSource.groupRequest(fcmRequestGroup)
    }

    override suspend fun acceptCrew(fcmRequestGroup: FcmRequestGroupDTO): Response<IsSuccessResponseDTO> {
        return firebaseFcmRemoteDataSource.acceptCrew(fcmRequestGroup)
    }


}