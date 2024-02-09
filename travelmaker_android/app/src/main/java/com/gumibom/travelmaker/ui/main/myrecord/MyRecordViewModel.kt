package com.gumibom.travelmaker.ui.main.myrecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyRecordViewModel @Inject constructor(

) : ViewModel() {

    private val _isFinish = MutableLiveData<Boolean>()
    val isFinish : LiveData<Boolean> = _isFinish


}