package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gumibom.travelmaker.data.dto.response.Position
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.ui.common.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MeetingPostViewModel_싸피"
@HiltViewModel
class MeetingPostViewModel @Inject constructor(

) : ViewModel(), CommonViewModel {

    /**
     * 모임 생성 데이터 모음
     */
    var title = ""
    var username = ""
    var content = ""
    var authDate = ""
    var startDate = ""
    var endDate = ""
    var position = Position()
    var maxMember = 0
    var minNative = 0
    var minTraveler = 0
    var deadline = ""
    var categoryList = mutableListOf<String>()
    private val _imageUrlList = mutableListOf<String>()


    private val _selectMeetingAddress = MutableLiveData<Address>()
    val selectMeetingAddress : LiveData<Address> = _selectMeetingAddress

    private val _urlLiveData = MutableLiveData<MutableList<String>>()
    val urlLiveData : LiveData<MutableList<String>> = _urlLiveData

    fun meetingSelectAddress(address : Address) {
        _selectMeetingAddress.value = address
    }

    fun addImageUrl(ImageUrl : String) {
        Log.d(TAG, "addImageUrl: $ImageUrl")
        _imageUrlList.add(ImageUrl)

        _urlLiveData.value = _imageUrlList
    }



    override fun setAddress(address: String) {

    }
}