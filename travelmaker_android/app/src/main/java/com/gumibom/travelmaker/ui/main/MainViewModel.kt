package com.gumibom.travelmaker.ui.main

import androidx.lifecycle.ViewModel
import com.gumibom.travelmaker.domain.signup.GetGoogleLocationUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getGoogleLocationUseCase: GetGoogleLocationUseCase,
) : ViewModel() {


    fun getNaverLatLng() {

    }
    fun getGoogleLatLng() {

    }

}