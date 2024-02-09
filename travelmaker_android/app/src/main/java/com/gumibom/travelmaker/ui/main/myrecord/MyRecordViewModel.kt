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

    private val _myRecordIng = MutableLiveData<List<PamphletItem>>()
    val myRecordIng : LiveData<List<PamphletItem>> = _myRecordIng

    private val _myRecordFinish = MutableLiveData<List<PamphletItem>>()
    val myRecordFinish : LiveData<List<PamphletItem>> = _myRecordFinish

    fun getMyRecord(userId : Long) {
        viewModelScope.launch {
            val pamphletItemList = getMyRecordUseCase.getMyRecord(userId)

            setTravelList(pamphletItemList)
        }
    }

    /**
     * 여행 중, 여행 완료 리스트로 분리하는 함수
     */
    private fun setTravelList(pamphletList : List<PamphletItem>) {
        val finishTravelList = mutableListOf<PamphletItem>()
        val ingTravelList = mutableListOf<PamphletItem>()

        for (pamphlet in pamphletList) {
            if (pamphlet.isFinish) {
                finishTravelList.add(pamphlet)
            } else {
                ingTravelList.add(pamphlet)
            }
        }
        _myRecordFinish.value = finishTravelList
        _myRecordIng.value = ingTravelList
    }
}