package com.gumibom.travelmaker.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.domain.login.LoginUseCase
import com.gumibom.travelmaker.model.BooleanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {


    private val _isLogin = MutableLiveData<BooleanResponse>()
    val isLogin : LiveData<BooleanResponse> = _isLogin

    fun login(loginRequestDTO: LoginRequestDTO) {
        viewModelScope.launch {
            _isLogin.value = loginUseCase.login(loginRequestDTO)
        }
    }

}