package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.ui.common.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeetingPostViewModel @Inject constructor(

) : ViewModel(), CommonViewModel {

    private val _selectMeetingAddress = MutableLiveData<Address>()
    val selectMeetingAddress : LiveData<Address> = _selectMeetingAddress

    fun meetingSelectAddress(address : Address) {
        _selectMeetingAddress.value = address
    }



    override fun setAddress(address: String) {

    }
}