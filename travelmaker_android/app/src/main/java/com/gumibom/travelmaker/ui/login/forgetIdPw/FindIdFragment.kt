package com.gumibom.travelmaker.ui.login.forgetIdPw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentLoginFindIdBinding



class FindIdFragment : Fragment() {

    private var _binding : FragmentLoginFindIdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginFindIdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInit()
        getCertificationNumber()
        checkCertificationNumber()
        nicknameCheck()
        phoneNumberCheck()

        // TODO 휴대폰 문자 인증 성공 시 아이디 찾기 버튼 활성화 -> 다이얼로그로 사용자의 ID 알려주기

    }

    private fun setInit() {
        binding.tilLoginFindId.error = null
    }

    /*
        문자 인증번호 요청하기
     */
    private fun getCertificationNumber() {
        binding.btnLoginFindIdPhone.setOnClickListener {
            val phoneError = binding.tilLoginFindIdPhone.error

            if (phoneError == null) {
                // TODO 서버에게 휴대폰 번호를 넘겨주고 Response에 따라 처리하기
                /**
                   Response 경우의 수
                    1. 해당하는 닉네임이 없을 때
                    2. 해당하는 휴대폰 번호가 없을 때
                    3. 성공했을 때 : 키보드 숨기기, 인증번호 입력창 띄우기, 타이머 띄우기
                 */
            }
        }
    }

    // 인증번호가 맞는지 서버 통신으로 확인하기
    private fun checkCertificationNumber() {
        binding.btnLoginFindIdCertification.setOnClickListener {
            // TODO 서버 통신으로 인증번호가 맞는지 확인하기
        }
    }

    // 닉네임이 비어있을 때 EditText에 error 표시하기
    private fun nicknameCheck() {
        binding.tieLoginFindId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            // 닉네임이 비어있으면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val nickname = binding.tieLoginFindId.text.toString()

                if (nickname.isEmpty()) {
                    binding.tilLoginFindId.error = "닉네임을 입력해주세요."
                } else {
                    binding.tilLoginFindId.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    // 휴대폰 번호 입력 error 체크
    private fun phoneNumberCheck() {
        binding.tieLoginFindIdPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            // 비어있으면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val phoneNumber = binding.tieLoginFindIdPhone.text.toString()

                if (phoneNumber.length != 11) {
                    binding.tilLoginFindIdPhone.error = "휴대폰 번호는 11자리여야 합니다."
                } else {
                    binding.tilLoginFindIdPhone.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

}