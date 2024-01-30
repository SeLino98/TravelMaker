package com.gumibom.travelmaker.ui.signup

import android.Manifest
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
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
class SignupActivity : AppCompatActivity(){
    private lateinit var binding : ActivitySignupBinding
    private lateinit var navController : NavController
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        Log.d(TAG, "onRequestPermissionsResult: ${requestCode} ${permissions} ${grantResults}")
//    }
    fun navigateToNextFragment() {
    Log.d(TAG, "navigateToNextFragment: gdgd")
        when(navController.currentDestination?.id){
            R.id.signupIdPwFramgnet-> navController.navigate(R.id.action_signupIdPwFramgnet_to_signupNicknameFragment)
            R.id.signupNicknameFragment->navController.navigate(R.id.action_signupNicknameFragment_to_signupLocationFragment)
            R.id.signupLocationFragment->navController.navigate(R.id.action_signupLocationFragment_to_signupGenderBirthdayFragment)
            R.id.signupGenderBirthdayFragment->navController.navigate(R.id.action_signupGenderBirthdayFragment_to_signupPhoneFragment)
            R.id.signupPhoneFragment->navController.navigate(R.id.action_signupPhoneFragment_to_signupProfileFragment)
            R.id.signupProfileFragment->navController.navigate(R.id.action_signupProfileFragment_to_loginFragment) //로그인으로 이동
        }
        updateProgressBar(true)
    }

    fun navigateToPreviousFragment() {
        Log.d(TAG, "navigateToPreviousFragment: ASDF")
        navController.navigateUp()
        updateProgressBar(false)
    }

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
//        val runtimePermissions = arrayOf(
//            Manifest.permission.CAMERA,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//        )
//        if (!checker.checkPermission(runtimePermissions)) {
//            checker.permitted = object : PermissionListener {
//                override fun onGranted() {
//                    //퍼미션 획득 성공일때
//                    /* permission check */
//                    Log.d(TAG, "onGranted: 토큰 수신 함 ")
////                    getTokenFCM()
//                }
//            }
//            checker.requestPermissionLauncher.launch(runtimePermissions)
//        } else { //이미 전체 권한이 있는 경우
//            /* permission check */
//            Log.d(TAG, "onGranted: 토큰 수신 함2 ")
////            getTokenFCM()
//        }
        // 프로그레스바 진행률 설정
        setProgressBar(20)
    }

    // 회원가입 화면 프로그레스바 진행률

    private fun updateProgressBar(isGo:Boolean) {
        var value = binding.pbSignup.progress
        if (isGo){//앞으로 가는 값
            value += 20 // 프로그레스바를 20% 증가
        }else{//뒤로 가는 값
            value -= 20 //20% 감소
        }
        setProgressBar(value)//담고 setProgressBar에 넘기고 넘긴 값을 에니메이팅 처리한다.

    }

    private fun setProgressBar(progress: Int) {
        // ObjectAnimator를 사용하여 부드러운 진행률 증가 애니메이션을 적용할 수 있음 ㅋㅋㅋ
        ObjectAnimator.ofArgb(binding.pbSignup, "progress", binding.pbSignup.progress, progress).apply {
            duration = 1000 //이동 시간을 ms 단위로 측정할 수 있는 로직
        }.start()
    }


}