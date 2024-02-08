package com.gumibom.travelmaker.data.repository.pamphlet

import com.gumibom.travelmaker.data.dto.response.PamphletResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface PamphletRepository {
    suspend fun makePamphlet(image : MultipartBody.Part, pamphletRequestDTO: RequestBody) : Response<PamphletResponseDTO>
}