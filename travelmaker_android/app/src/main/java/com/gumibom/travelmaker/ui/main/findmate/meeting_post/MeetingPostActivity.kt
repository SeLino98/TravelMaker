package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gumibom.travelmaker.databinding.ActivityMeetingPostBinding

class MeetingPostActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMeetingPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}