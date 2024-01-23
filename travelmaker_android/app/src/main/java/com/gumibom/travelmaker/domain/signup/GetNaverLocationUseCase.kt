package com.gumibom.travelmaker.domain.signup

import com.gumibom.travelmaker.data.dto.naver.AddressDTO
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepository
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.model.NaverAddress
import javax.inject.Inject

private const val TAG = "GetNaverLocationUseCase_싸피"

class GetNaverLocationUseCase @Inject constructor(
    private val naverLocationRepositoryImpl: NaverLocationRepository
) {
    suspend fun findNaverLocationSearch(
        idKey : String,
        secretKey : String,
        location : String,
        display : Int) : MutableList<Address> {

        // 여기서 DTO null 체크를 하고 model로 데이터 변환

        val response = naverLocationRepositoryImpl.findNaverLocationSearch(idKey, secretKey, location, display)

        var items = mutableListOf<AddressDTO>()
        var result = mutableListOf<Address>()

        // ResponseBody가 null일 경우 빈 리스트 반환
        if (response.isSuccessful && response.body() != null) {
            items = response.body()?.items ?: mutableListOf()
        }

        // 응답 값이 비어있지 않다면
        if (items.isNotEmpty()) {
            for (item in items) {
                val title = removeHtmlTags(item.title)
                val address = item.address

                result.add(Address(
                        title,
                        address
                    )
                )
            }
        }
        return result
    }

    // HTML 태그를 지우는 함수
    private fun removeHtmlTags(input: String): String {
        return input.replace(Regex("<[^>]*>"), "")
    }
}