package com.gumibom.travelmaker.ui.login

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gumibom.travelmaker.BuildConfig
import com.gumibom.travelmaker.constant.NO_LOGIN
import com.gumibom.travelmaker.constant.SUCCESS_LOGIN
import com.gumibom.travelmaker.data.dto.request.LoginRequestDTO
import com.gumibom.travelmaker.databinding.FragmentLoginBinding
import com.gumibom.travelmaker.databinding.FragmentLoginFindIdBinding
import com.gumibom.travelmaker.databinding.FragmentLoginFindPwBinding
import com.gumibom.travelmaker.model.User
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.util.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "LoginFragment_싸피"

@AndroidEntryPoint
class LoginFragment  : Fragment(){
    private var _binding :FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity:LoginActivity
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as LoginActivity
    }

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testClickEvent()
        signup()
        moveFindIdPwFragment()
        login()
        googleLogin()
        observeLoginData()

    }
    private fun testClickEvent(){
        binding.btnTest.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 회원가입 버튼 클릭 시 signupActivity로 넘어가는 함수
    private fun signup() {
        binding.btnLoginSignup.setOnClickListener {
            activity.moveSignupActivity(null)
        }
    }

    // 아이디 찾기 클릭 시 아이디 찾기 화면으로 전환
    private fun moveFindIdPwFragment() {
        // bundle로 id 찾기 눌렀는지 비번 재설정 눌렀는지 확인
        binding.tvLoginFindId.setOnClickListener {
            val bundle = bundleOf("idOrPassword" to "0")
            activity.navigateToNextFragment(bundle)
        }

        binding.tvLoginFindPassword.setOnClickListener {
            val bundle = bundleOf("idOrPassword" to "1")
            activity.navigateToNextFragment(bundle)
        }
    }

    // 아이디 패스워드를 입력하고 로그인을 하는 함수
    private fun login() {
        binding.btnLoginLogin.setOnClickListener {
            val id = binding.tieLoginId.text.toString()
            val password = binding.tieLoginPassword.text.toString()

            val loginRequestDTO = LoginRequestDTO(id, password)  // 서버에게 보낼 DTO
            val user = User(id, password) // 앱에 저장할 데이터

            // 아이디나 비밀번호가 비어있으면 다시 입력
            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), NO_LOGIN, Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.login(loginRequestDTO)
                ApplicationClass.sharedPreferencesUtil.addUser(user)
            }
        }
    }

    private fun googleLogin() {
        binding.btnGoogleLogin.setOnClickListener {
            activity.googleLogin()
        }
    }


    // LiveData를 관찰하여 로그인 성공 실패 여부를 확인하는 함수
    private fun observeLoginData() {
        loginViewModel.isLogin.observe(viewLifecycleOwner){ response->
            // 성공시 메인 화면 이동
            if (response.isSuccess) {
                Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                activity.moveMainActivity()
            }
            // 실패시 저장했던 앱 유저 데이터 삭제
            else {
                Toast.makeText(requireContext(), NO_LOGIN, Toast.LENGTH_SHORT).show()
                ApplicationClass.sharedPreferencesUtil.deleteUser()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initGoogleLogin(){


    }
}

