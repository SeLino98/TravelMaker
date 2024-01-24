package com.gumibom.travelmaker.ui.signup

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gumibom.travelmaker.BuildConfig
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.ActivitySignupBinding
import com.gumibom.travelmaker.util.PermissionChecker
import com.gumibom.travelmaker.util.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext


private const val TAG = "SignupActivity_싸피"

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입 네비게이션을 위한 navController 지정
        binding = ActivitySignupBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_signup)
            as NavHostFragment).navController
        }
        setContentView(binding.root)

                                                                                    //권한 체크
        val checker = PermissionChecker(this)
        val runtimePermissions = arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.CAMERA
        )
        if (!checker.checkPermission(runtimePermissions)) {
            checker.permitted = object : PermissionListener {
                override fun onGranted() {
                    //퍼미션 획득 성공일때
                    /* permission check */
                    Log.d(TAG, "onGranted: 토큰 수신 함 ")
//                    getTokenFCM()
                }
            }
            checker.requestPermissionLauncher.launch(runtimePermissions)
        } else { //이미 전체 권한이 있는 경우
            /* permission check */
            Log.d(TAG, "onGranted: 토큰 수신 함2 ")
//            getTokenFCM()
        }

        //


        // 프로그레스바 진행률 설정
        setProgressBar(20)
    }

    // 회원가입 화면 프로그레스바 진행률
    fun setProgressBar(progress : Int) {
        binding.pbSignup.progress = progress
    }


}