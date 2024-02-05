package com.gumibom.travelmaker.ui.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMypageBinding
import com.gumibom.travelmaker.ui.main.MainActivity

private const val TAG = "MainMyPageFragment_싸피"

class MainMyPageFragment:Fragment() {
    private var _binding:FragmentMainMypageBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        // 마이페이지에서 일어나는 주요 기능

        // 1. 뒤로가기 버튼 클릭 -> 메인화면으로 다시 이동
        previousBtn()
        // 2. 프로필사진 변경 버튼 클릭 -> 프로필사진 변경

        // 3. 닉네임 변경 -> edittext 를 눌러서 고침 (liveData)
        mypageNicknameEdit()
        // 4. 이메일 변경 -> edittext 를 눌러서 고침 (liveData)
        mypageEmailEdit()
        // 5. 회원정보 수정 버튼 클릭 -> 회원정보 수정할 다이얼로그 뜸

        // 6. 로그아웃 버튼 클릭 -> 로그인 상태에서 로그아웃 됨
        mypageLogout()
        // 7. 회원탈퇴 버튼 클릭 -> '정말 탈퇴하겠습니까?' 모달 나오면서 '탈퇴' 클릭 -> 탈퇴
        mypageWithdrawal()
    }

    private fun previousBtn() {
        val btnMypagePrevious  = binding.ibMypagePrevious
        // 뒤로가기 누르면, 뒤로가서 메인화면으로 감
        btnMypagePrevious.setOnClickListener {
            activity.navigationPop()
        }
    }

    private fun mypageNicknameEdit(){
        val nicknameContent = binding.etMypageNickname
        // 수정 함수 이므로, 유효성 검사 필요
        nicknameContent.onFocusChangeListener = View.OnFocusChangeListener{view, hasFocus ->
            // 포커스가 없을때 --> 유효성 검사 수행
            if (!hasFocus){
                // 닉네임 유효성 검사
                if (validateNickname(nicknameContent.text.toString())){
                    // 변경된 닉네임이 유효하다면 ---> 할일: 닉네임 변경을 업데이트
                }
            }
        }
    }

    private fun validateNickname(nickname:String): Boolean{
        if (nickname.isBlank()){
            Toast.makeText(context,"닉네임은 공백일 수 없습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun mypageEmailEdit(){
        val emailContent = binding.etMypageEmail
        // 수정 함수 이므로, 유효성 검사 필요
        emailContent.onFocusChangeListener = View.OnFocusChangeListener{view, hasFocus ->
            if (!hasFocus) {
                // 이메일 유효성 검사
                if (validateEmail(emailContent.text.toString())){
                    // 변경된 이메일이 유효하다면 ---> 할일: 이메일 변경을 업데이트
                }
            }
        }
    }

    private fun validateEmail(email:String): Boolean{
        if (email.isBlank()){
            Toast.makeText(context,"이메일은 공백일 수 없습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    
    private fun mypageLogout(){
        val btnLogout = binding.btnMypageLogout
        // 로그아웃 로직

    }

    private fun mypageWithdrawal(){
        val btnWithdrawal = binding.btnMypageWithdrawal
        // 회원탈퇴 로직
    }
}
