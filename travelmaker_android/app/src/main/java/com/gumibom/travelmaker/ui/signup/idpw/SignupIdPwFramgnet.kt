package com.gumibom.travelmaker.ui.signup.idpw

import android.content.Context
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
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupIdPwBinding
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "SignupIdPwFramgnet"
@AndroidEntryPoint
class SignupIdPwFramgnet : Fragment() {

    private lateinit var activity : SignupActivity
    private var _binding :FragmentSignupIdPwBinding? = null
    private val binding get() = _binding!!
    private var isNextPage = false
    private val signupViewModel : SignupViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupIdPwBinding.inflate(inflater,container,false)
        return binding.root
    }

    // onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Z. 이전/다음 버튼 활성화
        backAndNextNaviBtn()
        // Y. liveData가 지켜보다가 검사 돌리기
        observeData()
        // A-1. id_et가 비어 있으면 EditText에 error 표시 하기
        // A-2. id_et의 내용으로 valid check 돌리기 -> 1. 성공 or 2. 실패
        signupIdCheck()
        // B. A에서 성공 하면, id_et 내용으로 dup check 돌리기(ViewModel,liveData, observe) -> 1. 성공 or 2. 실패
        isDupId()
        // D-1. pw_et가 비어 있으면 EditText에 error 표시 하기
        // D-2. pw_et 내용으로 valid check 돌리기 -> 1. 성공 or 2. 실패
        signupPwCheck()
        // E. D 성공시, 다음 버튼 검정색으로 나오게 함...
        setNextToggle()
        // G. 지금 화면 종료
        onDestroyView()
    }

    private fun backAndNextNaviBtn(){
        val btnSignupPrevious = binding.btnSignup1Previous
        val btnSignupNext = binding.btnSignup1Next
        val errorSignupPw = binding.tilSignupPw.error

        btnSignupPrevious.setOnClickListener {
                activity.navigateToPreviousFragment()
            }
        if (errorSignupPw == null) {
            btnSignupNext.setOnClickListener {
                activity.navigateToNextFragment()
            }
        }
    }

    private fun observeData() {
        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) { it ->
            if (it == true) {
                Toast.makeText(requireContext(), "중복된 아이디 입니다.", Toast.LENGTH_LONG).show()
//                '중복된 아이디 입니다.' 라고 toast 뜨기
            } else {
                Toast.makeText(requireContext(), "가능한 아이디 입니다.", Toast.LENGTH_LONG).show()
//                '사용가능한 아이디 입니다.' 라고 toast 뜨기
            }
        }
    }

    /*
    signupIdCheck(){}
    아이디의 유효성 검사와 중복 검사를 위한 ...부모 함수

    id_et 가 비어 있으면 EditText에 error 표시 하기
    id_et 내용으로 valid check 돌리기
    둘다 성공하면 tilSignupId.error = null 됨
     */
    private fun signupIdCheck(){
        binding.etSignupId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }
            // id_et가 비어있으면 error 표시
            // id_et가 유효성 검사를 통과 못하면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val idContent = binding.etSignupId.text.toString()
                if (idContent.isEmpty()){
                    binding.tilSignupId.error = "ID를 입력 해주세요"
                } else {
                    binding.tilSignupId.error = null
                }
                if (!isValidateId(idContent)) {
                    binding.tilSignupId.error = "유효하지 않은 ID 입니다."
                } else {
                    binding.tilSignupId.error = null
                }
            }
            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
            }
        })
    }
    /*
    isValidateId(id: String): Boolean{}
    아이디의 유효성 검사를 위한... 자식 함수
    */
    private fun isValidateId(id: String): Boolean {
        if (id.length < 6 || !id.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+\$"))) {
            return false // 유효성 검사 실패
        }
        return true // 유효성 검사 통과
    }
    /*
    isDupId(){}
    아이디의 중복 검사를 위한... 자식 함수
    */
    private fun isDupId(){
        val isDupIdBtn = binding.btnSignupId

        isDupIdBtn.setOnClickListener{
            val idContents = binding.etSignupId.text.toString()
            signupViewModel.checkId(idContents)
        }
        // 1. 중복검사 버튼을 누를때마 뷰모델에 liveData를 만들고
        // 2. liveData에 옵저버를 달고
        // 3. 값이 바뀔때마다
        // 4. onChanged 메소드가 호출되는 것을 확인한다
    }
    /*
    signupPwCheck(){}
    비밀번호의 유효성 검사를 위한... 부모 함수
    */
    private fun signupPwCheck(){
        binding.etSignupPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            // id_et가 비어있으면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val pwContent = binding.etSignupPw.text.toString()
                if (pwContent.isEmpty()){
                    binding.tilSignupPw.error = "PW를 입력 해주세요"
                } else {
                    binding.tilSignupPw.error = null
                }
                if (!isValidatePw(pwContent)) {
                    binding.tilSignupPw.error = "유효하지 않은 PW 입니다."
                } else {
                    binding.tilSignupPw.error = null
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
    }

    /*
    isValidatePw(pw: String): Boolean {}
    비밀번호의 유효성 검사를 위한... 자식 함수
    */
    private fun isValidatePw(pw: String): Boolean {
        // 비밀번호 길이 및 문자 조건 검사
        if (pw.length < 8 || !pw.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+\$"))) {
            return false // 유효성 검사 실패
        }
        return true // 유효성 검사 통과
    }
    /*
    setNextToggle(){}
    다음으로 넘어가는 버튼이 찐해지는 부모함수
    */
    private fun setNextToggle(){
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)

        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) {it ->
            Log.d(TAG, "setNextToggle: ")
            if (it == false){
                // 이전 버튼의 색을 activeColor 로 변경하고, isNextPage 값을 true로 변경
                binding.btnSignup1Next.setTextColor(activeColor)
                isNextPage = true
            // 중복된 아이디가 맞는 경우
            }
        }
    }

    // N. 마지막.
    override fun onDestroyView() {
        super.onDestroyView()
    }
}