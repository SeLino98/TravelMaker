package com.gumibom.travelmaker.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.domain.signup.CheckDuplicatedIdUseCase
import com.gumibom.travelmaker.domain.signup.GetGoogleLocationUseCase
import com.gumibom.travelmaker.domain.signup.GetNaverLocationUseCase
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.model.NaverAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SignupViewModel_싸피"
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getNaverLocationUseCase: GetNaverLocationUseCase,
    private val getGoogleLocationUseCase: GetGoogleLocationUseCase,
    private val checkDuplicatedIdUseCase: CheckDuplicatedIdUseCase
) : ViewModel() {
    /*
        변수 사용하는 공간
     */

    // 우건
    // 가변형 변수는 바로 아래쪽에 몰아놓기
    var selectAddress = ""



    private val _address = MutableLiveData<String>()
    val address : LiveData<String> = _address

    private val _naverAddressList = MutableLiveData<MutableList<Address>>()
    val naverAddressList : LiveData<MutableList<Address>> = _naverAddressList

    private val _googleAddressList = MutableLiveData<MutableList<Address>>()
    val googleAddressList : LiveData<MutableList<Address>> = _googleAddressList
    // 우건

    // 지원
    private val _isDuplicatedId = MutableLiveData<Boolean>()
    val isDuplicatedId : LiveData<Boolean> = _isDuplicatedId
    // 지원

    // 인호
    // 인호

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
    
    // 구글 장소 검색 리스트 받기
    fun getGoogleLocation(location : String, apiKey : String) {
        viewModelScope.launch { 
            val googleAddressList = getGoogleLocationUseCase.findGoogleLocation(location, apiKey)
            
            _googleAddressList.value = googleAddressList
            Log.d(TAG, "getGoogleLocation: $googleAddressList")
        }
    }

    fun setAddress(address : String) {
        _address.value = address
        Log.d(TAG, "setAddress: $_address")
    }

    // TODO UseCase 주입 받아서 번호 인증 로직 구현하기

    // 우건

    // 지원
    fun checkId(id: String) {
        viewModelScope.launch {
//            val isCheckId = checkDuplicatedIdUseCase.checkDuplicatedId(id)!!
//            _isDuplicatedId.value = isCheckId
            _isDuplicatedId.value = false
        }
    }

    // 지원
}
