package com.gumibom.travelmaker.domain.meeting

import com.gumibom.travelmaker.data.dto.mygroup.MyMeetingGroupDTO
import com.gumibom.travelmaker.data.dto.mygroup.MyMeetingGroupDTOItem
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepository
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepositoryImpl
import javax.inject.Inject

class GetMyMeetingGroupListUseCase @Inject constructor(
    private val meetingRepositoryImpl: MeetingRepository
) {

    suspend fun getMyGroupList(id:Long):List<MyMeetingGroupDTOItem>{
        val response = meetingRepositoryImpl.getGroupList(id)
        var myGroupList = listOf<MyMeetingGroupDTOItem>()
        if (response.isSuccessful) {
            val body = response.body()?: mutableListOf()
            if (body.isNotEmpty()){
                myGroupList = body
            }
        }
        return myGroupList
    }


}