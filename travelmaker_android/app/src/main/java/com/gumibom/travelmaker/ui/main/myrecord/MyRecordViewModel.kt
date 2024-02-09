package com.gumibom.travelmaker.ui.main.myrecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.domain.pamphlet.GetMyRecordUseCase
import com.gumibom.travelmaker.model.pamphlet.PamphletItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecordViewModel @Inject constructor(
    private val getMyRecordUseCase: GetMyRecordUseCase
) : ViewModel() {

    private val _isFinish = MutableLiveData<Boolean>()
    val isFinish : LiveData<Boolean> = _isFinish

    private val _myRecord = MutableLiveData<List<PamphletItem>>()
    val myRecord : LiveData<List<PamphletItem>> = _myRecord

    fun getMyRecord(userId : Long) {
        viewModelScope.launch {
            val pamphletItemList = getMyRecordUseCase.getMyRecord(userId)
            _myRecord.value = pamphletItemList
        }
    }
}