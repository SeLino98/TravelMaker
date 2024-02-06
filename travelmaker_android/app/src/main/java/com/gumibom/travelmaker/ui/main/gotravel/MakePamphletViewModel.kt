package com.gumibom.travelmaker.ui.main.gotravel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakePamphletViewModel @Inject constructor(

) : ViewModel() {

    private val _categoryList = mutableListOf<String>()
    val category get() = _categoryList

    fun setCategoryList() {

    }

}