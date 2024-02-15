package com.gumibom.travelmaker.ui.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.gumibom.travelmaker.R

class DialogLoading(context : Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_lottie_animation)
    }
}