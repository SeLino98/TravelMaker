package com.gumibom.travelmaker.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMypageBinding
import com.gumibom.travelmaker.ui.dialog.ClickEventDialog
import com.gumibom.travelmaker.ui.main.MainActivity

private const val TAG = "MainMyPageFragment_싸피"

class MainMyPageFragment:Fragment() {
    private var _binding:FragmentMainMypageBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
        activity = context as  MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentMainMypageBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        // 1. 뒤로가기 버튼 클릭 -> 메인화면으로 다시 이동
        // 이미 toolbar에서 구현 되어있음.

        // 2. 프로필사진 변경 버튼 클릭 -> 프로필사진 변경
        selectMypagePicture()

        // 4. 이메일 변경 -> edittext 를 눌러서 고침 (liveData)
        editMyEmail()

        // 5. 회원정보 수정 버튼 클릭 -> 회원정보 수정할 다이얼로그 뜸
        // 다이얼로그가 뜨게 하는 작업
        // 다이얼로그에 적힌 정보를 수정된 edittext 내용으로 업데이트 하게 하는 작업
        // 다이얼로그가 닫히게 하는 작업

        // 6. 로그아웃 버튼 클릭 -> 로그인 상태에서 로그아웃 됨
        logoutMyAccount()
        // 7. 회원탈퇴 버튼 클릭 -> '정말 탈퇴하겠습니까?' 모달 나오면서 '탈퇴' 클릭 -> 탈퇴
        deleteMyAccount()
        // 8. 신뢰도 수준 보여주는 함수 <- 신뢰도 점수 구간에 맞게 drawable에서 img_trust_n 사진을 찾아서 그려줌
        mypageCheckTrustLevel()
    }

    private fun mypageCheckTrustLevel(){
        val btnCheckTrustLevel = binding.btnMypageCheckMyTrustlevel
        val getMyTrustLevel = binding.lottieMyTrustlevel
        val myTrustPoint = 700
        // ex) 내 신뢰도 점수 == 700점/900점 : myTrustPoint.. 그 정보를 레포지토리 부터 데려와야함
        btnCheckTrustLevel.setOnClickListener {
            when {
                myTrustPoint <= 200 -> {
                    // 200점 이하일 때의 로직
                }
                myTrustPoint in 201..300 -> {
                    // 201 ~ 300점일 때의 로직
                }
                myTrustPoint in 301..400 -> {
                    // 301 ~ 400점일 때의 로직
                }
                myTrustPoint in 401..500 -> {
                    // 401 ~ 500점일 때의 로직
                }
                myTrustPoint in 501..600 -> {
                    // 501 ~ 600점일 때의 로직
                }
                myTrustPoint in 601..700 -> {
                    // 601 ~ 700점일 때의 로직
                }
                myTrustPoint in 701..800 -> {
                    // 701 ~ 800점일 때의 로직
                }
                myTrustPoint in 801..900 -> {
                    // 801 ~ 900점일 때의 로직
                }
            }

        }
    }

    /*
    회원 프로필 사진 변경
    */
    private fun selectMypagePicture() {
        binding.ivMypageProfileEdit.setOnClickListener {
            // 플러스 버튼 누르면 ->
            // 1. 권한체크
            // 2. intent로 actionPick 이란 것 생성
            // 3. intent 타입을 image로 지정
            // 4. 콜백함수 실행 -> 성공이면? 파일형태를 URL로 변경 (실패면?)}
        }
    }

    /*
    이메일 수정
    */

    private fun editMyEmail(){
        val emailContent = binding.etMypageEmail
        val btnEmailEdit = binding.ivMypageEmailEdit
        // 수정 함수 이므로, 유효성 검사 필요
    }
    private fun validateEmail(email:String): Boolean{
        if (email.isBlank()){
            Toast.makeText(context,"이메일을 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /*
    로그아웃
     */
    private fun logoutMyAccount(){
        val btnLogout = binding.btnMypageLogout
        // 로그아웃 로직

    }
    /*
    회원탈퇴
    */
    private fun deleteMyAccount(){
        val btnWithdrawal = binding.btnMypageDeleteUser
        // 회원탈퇴 로직
    }
}
