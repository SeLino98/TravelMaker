package com.gumibom.travelmaker.ui.signup.phone

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.gumibom.travelmaker.databinding.FragmentSignupPhoneBinding
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import kotlinx.coroutines.launch

class SignupPhoneFragment : Fragment() {

    private var _binding : FragmentSignupPhoneBinding? = null
    private val binding get() = _binding!!
    private val signupViewModel : SignupViewModel by activityViewModels()

    private lateinit var phoneEditText : TextInputEditText
    private lateinit var certificationEditText : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneEditText = binding.tieSignupPhone
        certificationEditText = binding.tieSignupCertificationNumber

        getCertificationNumber()
        checkSecretNumber()
        phoneNumberCheck()

    }

    /*
        인증 요청 버튼을 누르면 서버에 문자 인증을 요청하고
        3분 타이머를 돌리기
     */
    private fun getCertificationNumber() {
        binding.btnSignupCertificationPhone.setOnClickListener {
            val phoneNumber = binding.tieSignupPhone.text.toString()
            val phoneNumberError = binding.tilSignupPhone.error

            // 정상 휴대폰 번호면 서버 통신
            if (phoneNumberError == null && phoneNumber.isNotEmpty()) {
                signupViewModel.sendPhoneNumber(phoneNumber)

                startTimer()
            }

        }
    }

    private fun checkSecretNumber() {
        binding.btnSignupCertificationNumber.setOnClickListener {
            // TODO 여기서 서버에 인증 요청이 맞는지 확인하는 코드 작성하기
            // TODO 인증이 성공하면 타이머 종료. 인증이 실패하면 타이머가 그대로 동작
            // 인증하기가 성공하면 타이머 끄기
            endTimer()
        }
    }

    // 인증번호 요청 시 사용자에게 3분 타이머가 돌아감
    private fun startTimer() {
        // 인증 번호 입력 화면 보여주기
        binding.layoutPhoneCertification.visibility = View.VISIBLE

        val timerText = binding.tvSignupTimer
        signupViewModel.startTimer(timerText)
    }

    private fun endTimer() {
        signupViewModel.endTimer()
    }


    // 뒤로 가기 버튼 클릭 시 EditText의 focus를 없애는 콜백 함수
    val callback = object : OnBackPressedCallback(true ) {
        override fun handleOnBackPressed() {
            if (phoneEditText.isFocused) {
                clearFocusAndHideKeyboard(phoneEditText)
            } else if (certificationEditText.isFocused) {
                clearFocusAndHideKeyboard(certificationEditText)
            } else {
                isEnabled = false
                // TODO 네비게이션 뒤로가기로 변경 필요
                activity?.onBackPressed()
            }
        }
    }

    // 휴대폰 번호 입력 error 체크
    private fun phoneNumberCheck() {
        binding.tieSignupPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            // 비어있으면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val phoneNumber = binding.tieSignupPhone.text.toString()

                if (phoneNumber.length != 11) {
                    binding.tilSignupPhone.error = "휴대폰 번호는 11자리여야 합니다."
                } else {
                    binding.tilSignupPhone.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }



    private fun clearFocusAndHideKeyboard(editText: EditText) {
        editText.clearFocus()
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}