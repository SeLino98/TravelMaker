package com.gumibom.travelmaker.data.repository.pamphlet

import com.gumibom.travelmaker.data.datasource.pamphlet.PamphletRemoteDataSource
import com.gumibom.travelmaker.data.datasource.pamphlet.PamphletRemoteDataSourceImpl
import com.gumibom.travelmaker.data.dto.response.PamphletResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class PamphletRepositoryImpl @Inject constructor(
    private val pamphletRemoteDataSourceImpl: PamphletRemoteDataSource
) : PamphletRepository{

    override suspend fun makePamphlet(
        image: MultipartBody.Part,
        pamphletRequestDTO: RequestBody
    ): Response<PamphletResponseDTO> {
        return pamphletRemoteDataSourceImpl.makePamphlet(image, pamphletRequestDTO)
    }
}