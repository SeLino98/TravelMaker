package com.gumibom.travelmaker.ui.signup

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.gumibom.travelmaker.domain.signup.CheckDuplicatedIdUseCase
import com.gumibom.travelmaker.domain.signup.GetGoogleLocationUseCase
import com.gumibom.travelmaker.domain.signup.GetKakaoLocationUseCase
import com.gumibom.travelmaker.domain.signup.SendPhoneNumberUseCase
import com.gumibom.travelmaker.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SignupViewModel_싸피"
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getGoogleLocationUseCase: GetGoogleLocationUseCase,
    private val sendPhoneNumberUseCase: SendPhoneNumberUseCase,
    private val checkDuplicatedIdUseCase: CheckDuplicatedIdUseCase,
    private val getKakaoLocationUseCase: GetKakaoLocationUseCase
) : ViewModel() {
    /*
        변수 사용하는 공간 시작
     */

    // 우건
    // 가변형 변수는 바로 아래쪽에 몰아놓기
    var bundle : Bundle? = null

    var loginId : String? = null
    var password : String? = null
    var email : String? = null

    // 가변형 변수 자리
    var selectAddress = ""

    // 가변형 변수 자리
    private val _address = MutableLiveData<String>()
    val address : LiveData<String> = _address

    private val _naverAddressList = MutableLiveData<MutableList<Address>>()
    val naverAddressList : LiveData<MutableList<Address>> = _naverAddressList

    private val _kakaoAddressList = MutableLiveData<MutableList<Address>>()
    val kakaoAddressList : LiveData<MutableList<Address>> = _kakaoAddressList

    private val _googleAddressList = MutableLiveData<MutableList<Address>>()
    val googleAddressList : LiveData<MutableList<Address>> = _googleAddressList

    // 타이머의 코루틴을 추적하는 변수
    private var timerJob : Job? = null

    // 우건

    // 지원
    private val _isDuplicatedId = MutableLiveData<Boolean>()
    val isDuplicatedId : LiveData<Boolean> = _isDuplicatedId
// 인호

    /*
        변수 사용하는 공간 끝
    */


    /*
        함수 사용하는 공간 시작
     */
// 우건

    // 카카오 장소 검색 리스트 받기
    fun getKakaoLocation(apiKey : String, location : String) {
        viewModelScope.launch {
            _kakaoAddressList.value = getKakaoLocationUseCase.getKakaoLocation(apiKey, location)
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

    // TODO UseCase 주입 받아서 번호 인증 로직 구현하기, 이쪽은 서버가 되면 그냥 하자
    fun sendPhoneNumber(phoneNumber : String) {
        viewModelScope.launch {
        }
    }

    // 코루틴으로 3분 타이머를 동작하는 함수
    fun startTimer(textView : TextView) {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            val totalTime = 3 * 60 // 3분

            for (time in totalTime downTo 1) {
                val mins = time / 60
                val secs = time % 60

                val timeFormat = "${mins}:${"%02d".format(secs)}" // 3:00 형태로 변환
                updateTimerUI(timeFormat, textView)
                delay(1000)
            }
        }
    }

    fun endTimer() {
        timerJob?.cancel()
    }

    private fun updateTimerUI(timeFormat : String, timerText : TextView) {
        timerText.text = timeFormat
    }
    // 우건

    //인호
    private val _favoriteList = MutableLiveData<List<String>>()
    val favoriteList: LiveData<List<String>> = _favoriteList
    fun updateFavoriteList(newList: List<String>) {
        _favoriteList.value = newList
    }
    fun saveToUserDTO() {
    }
    //인호 끝

    // 지원
    fun checkId(id: String) {
        viewModelScope.launch {
        // '중복된 아이디'인 지 의 기본값 = false: '중복이 아닌 아이디' 입니다.
            _isDuplicatedId.value = false
        }
    }
// 인호

}