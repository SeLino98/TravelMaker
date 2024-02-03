package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.ActivityMeetingPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    /**
     * 다음 버튼을 눌렀을 때 다음 화면으로 넘어가는 함수
     */
    fun navigateToNextFragment() {
        when(navController.currentDestination?.id){
            R.id.meetingPostDateFragment-> navController.navigate(R.id.action_meetingPostDateFragment_to_meetingPostPictureFragment)
            R.id.meetingPostPictureFragment->navController.navigate(R.id.action_signupNicknameFragment_to_signupLocationFragment)

        }
    }
}