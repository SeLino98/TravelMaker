package com.gumibom.travelmaker.ui.signup.idpw

import android.content.Context
import android.os.Bundle
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

private const val TAG = "SignupIdPwFramgnet"
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
    private fun backAndNextNaviBtn(){
        binding.tvSignupIdPwBefore.setOnClickListener {
            activity.navigateToPreviousFragment()
        }
        binding.tvSignupIdPwNext.setOnClickListener {
            activity.navigateToNextFragment()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noDuplicationBtn = binding.btnSignupId
        noDuplicationBtn.setOnClickListener{
            val idContents = binding.etSignupId.text.toString()
            signupViewModel.checkId(idContents)
        }
        observeData()
        backAndNextNaviBtn()
    }
    private fun observeData(){
        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner){it ->
            if (it == true){
                Toast.makeText(requireContext(),"중복된 아이디 입니다.", Toast.LENGTH_LONG).show()
//                '중복된 아이디 입니다.' 라고 toast 뜨기
            } else {
                Toast.makeText(requireContext(),"가능한 아이디 입니다.", Toast.LENGTH_LONG).show()
//                '사용가능한 아이디 입니다.' 라고 toast 뜨기
            }
        }
    }
    override fun onDetach() {
        super.onDetach()
    }
    private fun setNextToggle(){
        // 다음 Text가 활성화 되는 함수
        // isNextPage = true 되려면... 중복 아닌 아이디 & 비밀번호칸 차야함.
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)
        val notActiveColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) {it ->
            Log.d(TAG, "setNextToggle: ")
            if (it == false){
                binding.tvSignupIdPwNext.setTextColor(activeColor)
                isNextPage = true
            } else {
                binding.tvSignupIdPwNext.setTextColor(notActiveColor)
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