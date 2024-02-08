package com.gumibom.travelmaker.data.datasource.pamphlet

import com.gumibom.travelmaker.data.api.pamphlet.PamphletService
import com.gumibom.travelmaker.data.dto.response.PamphletResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class PamphletRemoteDataSourceImpl @Inject constructor(
    private val pamphletService: PamphletService
) : PamphletRemoteDataSource {

    override suspend fun makePamphlet(
        image: MultipartBody.Part,
        pamphletRequestDTO: RequestBody
    ): Response<PamphletResponseDTO> {
        return pamphletService.makePamphlet(image, pamphletRequestDTO)
    }
}