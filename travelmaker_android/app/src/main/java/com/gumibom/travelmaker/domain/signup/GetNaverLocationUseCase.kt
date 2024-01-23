package com.gumibom.travelmaker.domain.signup

import android.util.Log
import com.gumibom.travelmaker.constant.KOREAN_PATTERN
import com.gumibom.travelmaker.data.dto.AddressDTO
import com.gumibom.travelmaker.data.dto.NaverLocationDTO
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepository
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepositoryImpl
import com.gumibom.travelmaker.model.NaverAddress
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "GetNaverLocationUseCase_싸피"

class GetNaverLocationUseCase @Inject constructor(
    private val naverLocationRepositoryImpl: NaverLocationRepository
) {
    suspend fun findNaverLocationSearch(
        idKey : String,
        secretKey : String,
        location : String,
        display : Int) : MutableList<NaverAddress> {
        // 여기서 DTO null 체크를 하고 model로 데이터 변환

        val response = naverLocationRepositoryImpl.findNaverLocationSearch(idKey, secretKey, location, display)

        var items = mutableListOf<AddressDTO>()
        var result = mutableListOf<NaverAddress>()

        // ResponseBody가 null일 경우 빈 리스트 반환
        if (response.isSuccessful && response.body() != null) {
            items = response.body()?.items ?: mutableListOf()
        }

        // 응답 값이 비어있지 않다면
        if (items.isNotEmpty()) {
            for (item in items) {
                val title = removeHtmlTags(item.title)
                val address = item.address

                result.add(NaverAddress(
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