package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.ui.common.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MeetingPostViewModel_싸피"
@HiltViewModel
class MeetingPostViewModel @Inject constructor(

) : ViewModel(), CommonViewModel {

    private val _selectMeetingAddress = MutableLiveData<Address>()
    val selectMeetingAddress : LiveData<Address> = _selectMeetingAddress

    private val _imageUrlList = mutableListOf<String>()

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