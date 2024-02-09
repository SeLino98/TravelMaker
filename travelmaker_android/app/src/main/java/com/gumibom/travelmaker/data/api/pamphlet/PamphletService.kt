package com.gumibom.travelmaker.data.api.pamphlet

import com.gumibom.travelmaker.data.dto.response.PamphletResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PamphletService {
    @Multipart
    @POST("/personal-pamphlet")
    suspend fun makePamphlet(
        @Part image : MultipartBody.Part,
        @Part("makePPReqDto") pamphletRequestDTO : RequestBody
    ) : Response<PamphletResponseDTO>
}
