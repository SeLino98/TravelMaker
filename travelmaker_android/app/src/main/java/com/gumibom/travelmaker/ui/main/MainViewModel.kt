package com.gumibom.travelmaker.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.gumibom.travelmaker.constant.GOOGLE_API_KEY
import com.gumibom.travelmaker.constant.KAKAO_API_KEY
import com.gumibom.travelmaker.domain.signup.GetGoogleLocationUseCase
import com.gumibom.travelmaker.domain.signup.GetKakaoLocationUseCase
import com.gumibom.travelmaker.model.Address
import com.gumibom.travelmaker.ui.common.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel_싸피"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGoogleLocationUseCase: GetGoogleLocationUseCase,
    private val getKakaoLocationUseCase: GetKakaoLocationUseCase
) : ViewModel(), CommonViewModel {

    var address : Address? = null

    private val _kakaoAddressList = MutableLiveData<MutableList<Address>>()
    val kakaoAddressList : LiveData<MutableList<Address>> = _kakaoAddressList

    private val _googleAddressList = MutableLiveData<MutableList<Address>>()
    val googleAddressList : LiveData<MutableList<Address>> = _googleAddressList

    private val _selectAddress = MutableLiveData<Address>()
    val selectAddress : LiveData<Address> = _selectAddress

    fun getKakaoLatLng(location : String) {
        viewModelScope.launch {
            _kakaoAddressList.value = getKakaoLocationUseCase.getKakaoLocation(KAKAO_API_KEY, location)
        }
    }
    fun getGoogleLatLng(location : String) {
        viewModelScope.launch{
            _googleAddressList.value = getGoogleLocationUseCase.findGoogleLocation(location, GOOGLE_API_KEY)
        }
    }

    fun userSelectAddress(address : Address) {
        _selectAddress.value = address
        Log.d(TAG, "userSelectAddress: $address")
    }
    override fun setAddress(address: String) {

    }

    fun setSelectAddress(address : Address) {
        _selectAddress.value = address
    }

}