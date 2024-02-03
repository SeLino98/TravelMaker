package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class TimePickerHelper(private val context : Context) {

    fun pickTime(listener : (hour : Int, minute : Int) -> Unit) {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Appointment time")
            .build()

        picker.show((context as AppCompatActivity).supportFragmentManager, picker.toString())

        picker.addOnPositiveButtonClickListener {
            // 시간과 분을 리스너로 전달
            listener(picker.hour, picker.minute)
        }
    }
}