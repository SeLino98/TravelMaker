package com.gumibom.travelmaker.domain.meeting

import com.gumibom.travelmaker.data.dto.request.MarkerCategoryPositionRequestDTO
import com.gumibom.travelmaker.data.dto.request.MarkerPositionRequestDTO
import com.gumibom.travelmaker.data.dto.response.MarkerPositionResponseDTO
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepository
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepositoryImpl
import com.gumibom.travelmaker.model.MarkerPosition
import javax.inject.Inject

class GetMarkerCategoryPositionsUseCase @Inject constructor(
    private val meetingRepositoryImpl: MeetingRepository
) {
    suspend fun getMarkerCategoryPositions(markerCategoryPositionRequestDTO: MarkerCategoryPositionRequestDTO) : List<MarkerPosition>{
        val response = meetingRepositoryImpl.getMarkerCategoryPositions(markerCategoryPositionRequestDTO)
        var markerPositionList = listOf<MarkerPosition>()
        if (response.isSuccessful) {
            // 데이터가 null 일 경우 빈 리스트
            val body = response.body() ?: mutableListOf()

            if (body.isNotEmpty()) {
                markerPositionList = translateMarkerList(body)
            }
        }
        return markerPositionList
    }

    /**
     * MarkerPositionResponseDTO에 속성인
     * id와 position의 null 체크
     */
    fun MarkerPositionResponseDTO.toMarkerPosition(): MarkerPosition? {
        // 'let' 스코프 함수를 사용하여 id와 position이 모두 null이 아닐 때만 실행
        return this.position?.let { position ->
            this.id?.let { id ->
                // id와 position이 모두 null이 아닌 경우 MarkerPosition 객체 생성
                MarkerPosition(id, position)
            }
        }
    }

    /**
     * Null 체크를 하고 map으로 걸러서 새로운 List를 반환
     */
    private fun translateMarkerList(body : MutableList<MarkerPositionResponseDTO>) : List<MarkerPosition>{
        val markerPositionList : List<MarkerPosition> = body.mapNotNull {
            it.toMarkerPosition()
        }
        return markerPositionList
    }
}