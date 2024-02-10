package com.gumibom.travelmaker.ui.main.myrecord.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.domain.pamphlet.GetAllMyRecordUseCase
import com.gumibom.travelmaker.model.pamphlet.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecordDetailViewModel @Inject constructor(
    private val getAllMyRecordUseCase: GetAllMyRecordUseCase
) : ViewModel() {

    private val _myAllRecord = MutableLiveData<MutableList<Record>>()
    val myAllRecord : LiveData<MutableList<Record>> = _myAllRecord

    fun getMyAllRecord(pamphletId : Long) {
        viewModelScope.launch {
            _myAllRecord.value = getAllMyRecordUseCase.getAllMyRecord(pamphletId)
        }
    }
}