package com.gumibom.travelmaker.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentLoginBinding
import com.gumibom.travelmaker.databinding.FragmentLoginFindIdBinding
import com.gumibom.travelmaker.databinding.FragmentLoginFindPwBinding
import com.gumibom.travelmaker.ui.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment  : Fragment(){

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: LoginActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as LoginActivity
    }
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

        moveFindIdPwFragment()
        login()
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
        }
    }
}