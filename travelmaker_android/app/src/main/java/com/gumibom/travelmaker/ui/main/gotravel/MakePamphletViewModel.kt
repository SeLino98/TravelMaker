package com.gumibom.travelmaker.ui.main.gotravel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakePamphletViewModel @Inject constructor(

) : ViewModel() {

    val categoryList = mutableListOf<String>()

    fun setCategoryList() {

    }

}