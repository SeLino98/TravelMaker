package com.gumibom.travelmaker.ui.signup

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.data.dto.request.RequestDto
import com.gumibom.travelmaker.data.dto.request.SignInUserDataRequestDTO
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
import com.gumibom.travelmaker.model.SignInUserDataRequest
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


    //모임 데이터들을 저장하는 유저데이터dto
    private val _userDTO = MutableLiveData<SignInUserDataRequest>()
    val userDTO :LiveData<SignInUserDataRequest> = _userDTO

    //서버로 보낼 데이터
    private val _userRequestDTO = MutableLiveData<SignInUserDataRequestDTO>()
    val userRequestDTO : LiveData<SignInUserDataRequestDTO> = _userRequestDTO
    fun saveUserInfoIDPW(userId:String,userPassword:String){
        _userDTO.value!!.requestDto.username = userId; //로그인 시 필요한 아이디
        _userDTO.value!!.requestDto.password = userPassword
    }//page 1
    fun saveUserInfoNick(nickName:String){
        _userDTO.value!!.requestDto.nickname = nickName
    }//page 2
    fun saveUserInfoAddress(address:String){
        //나눠서 저장 town이랑 address
        _userDTO.value!!.requestDto.town = address

        //스플릿?

        _userDTO.value!!.requestDto.nation = address
    }//page 3
    fun saveUserInfoGenderBirth(gender : String, birth:String ){
        _userDTO.value!!.requestDto.gender = gender
        _userDTO.value!!.requestDto.birth = birth
    }//page 4
    fun saveUserInfoSavePhoneNum(phone:String){
        _userDTO.value!!.requestDto.phone = phone
    }//page 5
    fun saveUserInfoSaveProfileCategory(profileImage:String, categoryList : List<String>){
        _userDTO.value!!.image = profileImage //멀티 파트로 저장.
        _userDTO.value!!.requestDto.categories = categoryList
    }//page 6
    fun saveUserInfoAllData(){

        _userRequestDTO.value = SignInUserDataRequestDTO(userDTO.value!!.image,
            (RequestDto(birth = userDTO.value!!.requestDto.birth,
                userDTO.value!!.requestDto.categories,
                userDTO.value!!.requestDto.email,
                userDTO.value!!.requestDto.gender,
                userDTO.value!!.requestDto.nation,
                userDTO.value!!.requestDto.nickname,
                userDTO.value!!.requestDto.password,
                userDTO.value!!.requestDto.phone,
                userDTO.value!!.requestDto.town,
                userDTO.value!!.requestDto.username)))//val /var
        viewModelScope.launch{
            //여따 LoginRequsetDTO 정보를 다 담아야 됨.
            // setUserDataToUserDTO() //데이터 정상으로 받으면 ㅡ<수정하기>ㅡ
                isSignup.value = saveUserInfoUseCase.saveUserInfo(userRequestDTO.value!!)
                Log.d(TAG, "saveToUserDTO: ")


        }


        




    }
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


    private val _isSignup = MutableLiveData<IsSuccessResponseDTO>()
            val isSignup = _isSignup


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

