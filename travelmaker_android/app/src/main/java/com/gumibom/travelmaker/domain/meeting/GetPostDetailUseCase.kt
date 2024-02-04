package com.gumibom.travelmaker.domain.meeting

import com.gumibom.travelmaker.data.dto.response.MeetingPostDTO
import com.gumibom.travelmaker.data.dto.response.Position
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepository
import com.gumibom.travelmaker.model.PostDetail
import javax.inject.Inject

private const val TAG = "GetPostDetailUseCase"
class GetPostDetailUseCase @Inject constructor(
    private val meetingRepositoryImpl: MeetingRepository
){
    // 바텀 네비게이션에 있는 정보들을 뿌려주는 역할
    // DTO들로 받은 데이터들을 Model로 변환하고 return시킨다.
    // ViewModel에서 사용ㄷ회어허ㅏㄴ다 ㄴ알허 라ㅣ호
    suspend fun getPostDetail(postId:Long):PostDetail {
        val response = meetingRepositoryImpl.getPostDetail(postId)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return convertAddressModel(body)
            }
        }
        return convertAddressModel(MeetingPostDTO())
    }
    private fun convertAddressModel(body : MeetingPostDTO) : PostDetail{
        return PostDetail(
            authDate = body.authDate ?: "",
            categories = body.categories.orEmpty(), // Converts null to an empty list
            content = body.content ?: "",
            deadline = body.deadline ?: "",
            endDate = body.endDate ?: "",
            imgUrlMain = body.imgUrlMain ?: "",
            imgUrlSub = body.imgUrlSub ?: "",
            imgUrlThr = body.imgUrlThr ?: "",
            memberMax = body.memberMax ?: 0,
            nativeMin = body.nativeMin ?: 0,
            positionName = body.position?.name?:"",
            startDate = body.startDate ?: "",
            title = body.title ?: "",
            travelerMin = body.travelerMin ?: 0,
            username = body.username ?: ""
        )
    }
}