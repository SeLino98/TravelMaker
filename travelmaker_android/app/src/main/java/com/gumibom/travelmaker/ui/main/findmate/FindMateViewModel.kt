package com.gumibom.travelmaker.ui.main.findmate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.data.dto.request.FcmRequestGroupDTO
import com.gumibom.travelmaker.domain.firebase.FirebaseAcceptCrewUseCase
import com.gumibom.travelmaker.domain.firebase.FirebaseRequestGroupUseCase
import com.gumibom.travelmaker.domain.meeting.GetMarkerPositionsUseCase
import com.gumibom.travelmaker.model.BooleanResponse
import com.gumibom.travelmaker.model.MarkerPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "FindMateViewModel_싸피"
@HiltViewModel
class FindMateViewModel @Inject constructor(
    private val getMarkerPositionsUseCase : GetMarkerPositionsUseCase,
    private val requestGroupUseCase: FirebaseRequestGroupUseCase,
    private val acceptCrewUseCase: FirebaseAcceptCrewUseCase
) : ViewModel() {
    // 현재 위치의 위도 경도
    var currentLatitude = 0.0
    var currentLongitude = 0.0

    private val _markerList = MutableLiveData<List<MarkerPosition>>()
    val markerList : LiveData<List<MarkerPosition>> = _markerList




    // 서버에서 내 근방 위치 모임들을 가져옴
    fun getMarkers(latitude : Double, longitude : Double, radius : Double) {
        viewModelScope.launch {
            _markerList.value = getMarkerPositionsUseCase.getMarkerPositions(latitude, longitude, radius)
        }
    }

    // 구글 api로 장소 검색 후 위도 경도 가져오기
    fun getGoogleLatLng() {

    }

    // 네이버 api로 장소 검색 후 위도 경도 가져오기
    fun getNaverLatLng() {

    }
}