package com.gumibom.travelmaker.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupIdPwBinding

private const val TAG = "SignupIdPwFragment_싸피"
class SignupIdPwFragment : Fragment() {
//  0. var, val 설정
    private var _binding : FragmentSignupIdPwBinding? = null
    private val binding get() = _binding!!
    private val signupViewModel : SignupViewModel by activityViewModels()
    private var isNextPage = false

// 1. onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupIdPwBinding.inflate(inflater,container,false)
        return binding.root
    }

// 2. onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 아이디 유효성 검사를 통과 하면, 중복검사 버튼이 활성화됨.
        // 2-1-1.아이디 유효성 검사
        binding.etSignupId.addTextChangedListener(object: TextWatcher{
            // a. 내가 작성 하면서 이벤트 발동
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            // b. 입력란에 변화가 있을시 발동
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            // c. 입력이 끝났을때 발동
            override fun afterTextChanged(s: Editable?) {
                // 텍스트가 변경된 후의 로직
                s?.let {
                    // 유효성 검사 실행
                    val isValidId = validateId(it.toString())
                    // 중복 확인 버튼 활성화/비활성화
                    binding.btnSignupId.isEnabled = isValidId
                }
            }
        })
        // 2-1-2. 비밀번호 유효성 검사
        binding.etSignupPw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(p: Editable?) {
                p?.let {
                    // 유효성 검사 실행
                    val isValidPassword = validatePassword(it.toString())
                    // 중복 확인 버튼 활성화/비활성화
                    binding.btnSignup1Next.isEnabled = isValidPassword
            }
        })

        // 2-2. 불변형 객체로 양방향 바인딩의 '아이디 중복검사버튼'(이하 '중복버튼').. 정의
        val noDuplicationBtn = binding.btnSignupId
        // 2-3. 중복버튼에 setOnClickListener 달기.

            noDuplicationBtn.setOnClickListener{
            // 2-4. idContents 라는 id_et창에 친 내용을 string으로 변환한 내용.. 정의

            val idContents = binding.etSignupId.text.toString()
            // 2-5. checkId라는 (뷰모델에서 정의했던) 함수로 idContents를 검사함
                signupViewModel.checkId(idContents)
            }
            // 1) 버튼을 누를때마다 뷰모델에 liveData를 만들고, 2) 데이터에 옵저버를 달고,
            // 3) 값이 바뀔 때마다 4) onChanged 메소드가 호출되는 것을 확인한다

        // 2-6. observeData() 함수를 작동 시킨다.
        observeData()
    }

    // 3-1. validateId(). 아이디 유효성 검사 함수
    private fun validateId(id: String): Boolean {
        // 예: 아이디 길이 및 문자 조건 검사
        if (id.length > 6 || !id.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+\$"))) {
            return false // 유효성 검사 실패
        }
        return true // 유효성 검사 통과
    }

    // 3-2. validatePassword(). 비밀번호 유효성 검사 함수

    private fun validatePassword(password: String): Boolean {
        // 비밀번호 유효성 검사: 최소 8자 이상, 알파벳, 숫자, 특수 문자를 포함해야 함
        return password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"))
    }

}

    // next 버튼이 활성화 되는 함수
    // isNextPage = true 되려면..?
    // 1)아이디 유효성 검사 통과 + 2)아이디 중복검사 통과 + 3)비밀번호 중복검사 통과 되야함
    private fun setNextToggle(){
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)
        val nonActiveColor = ContextCompat.getColor(requireContext(), R.color.light_gray)
        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) {it ->
            Log.d(TAG, "setNextToggle: ")
            // 중복된 아이디가 아닌 경우
            if (it == false){
                // 이전 버튼의 색을 activeColor 로 변경하고, isNextPage 값을 true로 변경
                binding.btnSignup1Next.setTextColor(activeColor)
                isNextPage = true
            // 중복된 아이디가 맞는 경우
            } else {
                // isNextPage 값을 false로 변경
                isNextPage = false
            }
        }
    }

    // 다음 버튼이 활성화 되어있을 시 페이지 전환하는 함수
    private fun openNextPage(){
        if (isNextPage) {
//            TODO 네비게이션으로 다음 페이지 전환 기능 구현 필요
        }
    }

// N. 마지막.
    override fun onDestroyView() {
        super.onDestroyView()
    }
}