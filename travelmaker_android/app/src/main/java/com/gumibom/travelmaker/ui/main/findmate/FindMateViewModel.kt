package com.gumibom.travelmaker.ui.main.findmate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.domain.meeting.GetMarkerPositionsUseCase
import com.gumibom.travelmaker.model.MarkerPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindMateViewModel @Inject constructor(
    private val getMarkerPositionsUseCase : GetMarkerPositionsUseCase
) : ViewModel() {

    private val _markerList = MutableLiveData<List<MarkerPosition>>()
    val markerList : LiveData<List<MarkerPosition>> = _markerList


    fun getMarkers(latitude : Double, longitude : Double, radius : Double) {
        viewModelScope.launch {
            _markerList.value = getMarkerPositionsUseCase.getMarkerPositions(latitude, longitude, radius)
        }
    }
}