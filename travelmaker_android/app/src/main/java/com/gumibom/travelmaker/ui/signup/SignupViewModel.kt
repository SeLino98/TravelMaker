package com.gumibom.travelmaker.ui.signup

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.data.dto.request.UserRequestDTO
import com.gumibom.travelmaker.data.dto.response.IsSuccessResponseDTO
import com.gumibom.travelmaker.data.dto.response.SignInResponseDTO

import com.gumibom.travelmaker.domain.signup.CheckDuplicatedIdUseCase
import com.gumibom.travelmaker.domain.signup.CheckDuplicatedNicknameUseCase
import com.gumibom.travelmaker.domain.signup.GetGoogleLocationUseCase

import com.gumibom.travelmaker.domain.signup.GetKakaoLocationUseCase
import com.gumibom.travelmaker.domain.signup.CheckSecretNumberUseCase
import com.gumibom.travelmaker.domain.signup.SaveUserInfoUseCase

import com.gumibom.travelmaker.domain.signup.SendPhoneNumberUseCase
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.model.BooleanResponse
import com.gumibom.travelmaker.model.GoogleUser
import com.gumibom.travelmaker.ui.common.CommonViewModel
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
    private val checkDuplicatedNicknameUseCase: CheckDuplicatedNicknameUseCase,
    private val getKakaoLocationUseCase: GetKakaoLocationUseCase,
    private val saveUserInfoUseCase :SaveUserInfoUseCase
) : ViewModel(), CommonViewModel {


    /*
        변수 사용하는 공간 시작
     */
    init {

    }

    // 우건
    var bundle : Bundle? = null
    // 가변형 변수 자리

    var loginId : String? = null
    var password : String? = null
    var nickname : String? = null
    var email : String? = null

    // 가변형 변수 자리
    // 가변형 변수 자리

    //인호
    private val _favoriteList = MutableLiveData<List<String>>()
    val favoriteList: LiveData<List<String>> = _favoriteList
    fun updateFavoriteList(newList: List<String>) {
        _favoriteList.value = newList
    }

    private lateinit var userInfo:UserRequestDTO
    fun setUserDataToUserDTO(category: Int, userId: String,
                             password: String, email: String,
                             nickname: String, gender: Boolean,
                             birth: String, phoneNum: String,
                             imgURL: String, town: String,
                             belief: Double) {
        userInfo = UserRequestDTO(
            category = category,
            userId = userId,
            password = password,
            email = email,
            nickname = nickname,
            gender = gender,
            birth = birth,
            phoneNum = phoneNum,
            imgURL = imgURL,
            town = town,
            belief = belief
        )
    }
    private val _isSignup = MutableLiveData<IsSuccessResponseDTO>()
            val isSignup = _isSignup

    fun saveToUserDTO() {
        //여따 LoginRequsetDTO 정보를 다 담아야 됨.
       // setUserDataToUserDTO() //데이터 정상으로 받으면 ㅡ<수정하기>ㅡ
        viewModelScope.launch {
            isSignup.value = saveUserInfoUseCase.saveUserInfo(userInfo)
            Log.d(TAG, "saveToUserDTO: ")
        }
    }
    private val _isDupNick = MutableLiveData<SignInResponseDTO>()
    val isDupNick:LiveData<SignInResponseDTO> = _isDupNick


    fun checkDupNickName(nickName:String){
        viewModelScope.launch {
            _isDupNick.value = checkDuplicatedNicknameUseCase.checkDuplicatedNick(nickName)
        }
    }
    //인호 끝

// 우건

    var selectAddress = ""
    var selectGender = ""
    var selectBirthDate = ""
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

    // 지원


    private val _isDuplicatedNickname = MutableLiveData<Boolean>()
    val isDuplicatedNickname : LiveData<Boolean> = _isDuplicatedNickname

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

    override fun setAddress(address : String) {
        _address.value = address
        Log.d(TAG, "setAddress: ${_address.value}")
    }

    // TODO UseCase 주입 받아서 번호 인증 로직 구현하기, 이쪽은 서버가 되면 그냥 하자
    fun sendPhoneNumber(phoneNumber : String) {
        viewModelScope.launch {
            val dto = sendPhoneNumberUseCase.sendPhoneNumber(phoneNumber)
            Log.d(TAG, "sendPhoneNumber: $dto")
        }
    }

    // TODO 문자인증을 받고 번호가 맞는지 검증하는 함수
    fun isCertification() {

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

    fun setIdPassword() {
        val googleUser = bundle?.getBundle("googleUser")
//        loginId = googleUser.e
    }

    // 우건



    private val _isDuplicatedId = MutableLiveData<SignInResponseDTO>()
    val isDuplicatedId : LiveData<SignInResponseDTO> = _isDuplicatedId


    // 지원
    fun checkId(id: String) {
        viewModelScope.launch {
        // '중복된 아이디' 인 지 의 기본값 = false: '중복이 아닌 아이디' 입니다.

        // '중복된 아이디'여부의 기본값 = false ==> '중복이 아닌 아이디' 입니다.
            _isDuplicatedId.value = checkDuplicatedIdUseCase.checkDuplicatedId(id)
        }
    }
    fun checkNickname(nickname: String) {
        viewModelScope.launch {
        // '중복된 닉네임'여부의 기본값 = false ==> '중복이 아닌 닉네임' 입니다.
            _isDuplicatedNickname.value = false
        }
    }

// 인호
}

