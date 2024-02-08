package com.gumibom.travelmaker.data.datasource.pamphlet

import com.gumibom.travelmaker.data.dto.request.PamphletRequestDTO
import com.gumibom.travelmaker.data.dto.response.PamphletResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart

interface PamphletRemoteDataSource {
    suspend fun makePamphlet(image : MultipartBody.Part, pamphletRequestDTO: RequestBody) : Response<PamphletResponseDTO>
}