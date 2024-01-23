package com.gumibom.travelmaker.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.data.dto.AddressDTO
import com.gumibom.travelmaker.domain.signup.GetNaverLocationUseCase
import com.gumibom.travelmaker.model.NaverAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SignupViewModel_싸피"
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getNaverLocationUseCase: GetNaverLocationUseCase
) : ViewModel() {
    /*
        변수 사용하는 공간

     */

    // 우건
    var address = ""


    private val _naverAddressList = MutableLiveData<MutableList<NaverAddress>>()
    val naverAddressList : LiveData<MutableList<NaverAddress>> = _naverAddressList


    // 우건






    /*
        함수 사용하는 공간
     */

    // 우건

    // 네이버 장소 검색 리스트 받기
    fun getNaverLocation(idKey : String, secretKey : String, location : String, display : Int) {
        // TODO 네이버 장소 데이터 받기 구현 필요
        viewModelScope.launch {
            val naverAddressList = getNaverLocationUseCase.findNaverLocationSearch(idKey, secretKey, location, display)
            _naverAddressList.value = naverAddressList
            Log.d(TAG, "getNaverLocation: $naverAddressList")
        }
    }


    // 우건

}