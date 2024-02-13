package com.gumibom.travelmaker.ui.signup.nickname

import android.content.Context
import android.os.Build
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
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupNicknameBinding
import com.gumibom.travelmaker.model.GoogleUser
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import com.gumibom.travelmaker.util.SharedPreferencesUtil
import com.gumibom.travelmaker.ui.signup.idpw.TAG
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "SignupNicknameFramgnet"
@AndroidEntryPoint
class SignupNicknameFragment : Fragment(){
    private lateinit var activity : SignupActivity
    private var isNextPage = true
    private var _binding :FragmentSignupNicknameBinding? = null
    private val binding get() = _binding!!
    private val signupViewModel : SignupViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as SignupActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val googleUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("googleUser", GoogleUser::class.java)
        } else {
            arguments?.getParcelable("googleUser")
        }
        signupViewModel.loginId = googleUser?.email
        signupViewModel.email = googleUser?.email
        signupViewModel.password = googleUser?.uId

        Log.d(TAG, "googleLogin: ${signupViewModel.email}")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupNicknameBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Z. 이전/다음 버튼 활성화
        backAndNextNaviBtn()
        checkDuplicateNickName()
        observeViewModel()
    }
    private fun observeViewModel(){
        signupViewModel.isDupNick.observe(viewLifecycleOwner){
            binding.tvSignupNicknameNext.isEnabled = it.isSuccess
        }
    }
    private fun checkDuplicateNickName(){
        binding.btnSignupNickname.setOnClickListener {
            signupViewModel.checkDupNickName(binding.etSignupNickname.text.toString())
        }

        // Y. liveData가 지켜보다가 중복 검사 돌리기
        observeData()
        // A-1. nickname_et가 비어 있으면 EditText에 error 표시 하기
        // A-2. nickname_et의 내용으로 valid check 돌리기 -> 1. 성공 or 2. 실패
        signupNicknameCheck()
        // B. A에서 성공 하면, id_et 내용으로 dup check 돌리기(ViewModel,liveData, observe) -> 1. 성공 or 2. 실패

    }
    private fun backAndNextNaviBtn(){
        val btnSignupPrevious = binding.tvSignupNicknamePrevious
        val btnSignupNext = binding.tvSignupNicknameNext
        // 뒤로가기 버튼 기능은 늘 가능
        btnSignupPrevious.setOnClickListener {
            activity.navigateToPreviousFragment()
        // 앞으로가기 버튼 기능을 특정한 경우에만 가능
        }
        btnSignupNext.setOnClickListener {
                activity.navigateToNextFragment()
        }
    }

    private fun observeData() {
        signupViewModel.isDuplicatedNickname.observe(viewLifecycleOwner) { it ->
            if (it == true) {
                Toast.makeText(requireContext(), "중복된 닉네임 입니다.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "가능한 닉네임 입니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    /*
    signupNicknameCheck()
    닉네임 유효성 검사와 중복검사를 위한... 부모 함수
    */
    private fun signupNicknameCheck(){
        binding.etSignupNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            // nm_et가 비어있으면 error 표시
            // nm_et가 유효성 검사를 통과 못하면 error 표시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val nicknameContent = binding.etSignupNickname.text.toString()
                if (nicknameContent.isEmpty()){
                    binding.tilSignupNickname.error = "닉네임을 입력 해주세요"
                } else {
                    binding.tilSignupNickname.error = null
                }
                isDupNickname()
                // 해야할일: isDupNickname()에 값에 따라, tilSignupNickname.error 값도 결정되게 만들기. 근데 liveData에 연결되어서, 어케 해야할지..
                setNextToggle()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    /*
    isDupNickname()
    닉네임 중복검사를 위한... 자식 함수
    */

    private fun isDupNickname(){
        binding.btnSignupNickname.setOnClickListener{
            val nicknameContents = binding.etSignupNickname.text.toString()
            signupViewModel.checkNickname(nicknameContents)
        }
        // 1. 중복검사 버튼을 누를때마 뷰모델에 liveData를 만들고
        // 2. liveData에 옵저버를 달고
        // 3. 값이 바뀔때마다
        // 4. onChanged 메소드가 호출되는 것을 확인한다
    }
    override fun onDetach() {
        super.onDetach()
    }
    /*
    setNextToggle(){}
    다음으로 넘어가는 버튼이 찐해지는 부모함수
    */
    private fun setNextToggle(){
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)
        val nonActiveColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        signupViewModel.isDuplicatedNickname.observe(viewLifecycleOwner) {it ->
            Log.d(TAG, "setNextToggle: ")
            if (binding.tilSignupNickname.error == null){
                // 이전 버튼의 색을 activeColor 로 변경하고, isNextPage 값을 true로 변경
                binding.tvSignupNicknameNext.setTextColor(activeColor)
                isNextPage = true
                // 중복된 아이디가 맞는 경우
            } else {
                binding.tvSignupNicknameNext.setTextColor(nonActiveColor)
                isNextPage = false
            }
        }
    }

    // 마지막.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}