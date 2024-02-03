package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.ActivityMeetingPostBinding

class MeetingPostActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMeetingPostBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingPostBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_meeting_post)
                    as NavHostFragment).navController
        }
        setContentView(binding.root)
    }
}