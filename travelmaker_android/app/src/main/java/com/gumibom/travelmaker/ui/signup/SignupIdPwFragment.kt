package com.gumibom.travelmaker.ui.signup

import android.os.Bundle
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

        val noDuplicationBtn = binding.btnSignupId
        noDuplicationBtn.setOnClickListener{
            val idContents = binding.etSignupId.text.toString()
            signupViewModel.checkId(idContents)
        }
        observeData()
    }
// 3. observeData()
    private fun observeData(){
        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner){it ->
            if (it == true){
                Toast.makeText(requireContext(),"중복된 아이디 입니다.",Toast.LENGTH_LONG).show()
//                '중복된 아이디 입니다.' 라고 toast 뜨기
            } else {
                Toast.makeText(requireContext(),"가능한 아이디 입니다.",Toast.LENGTH_LONG).show()
//                '사용가능한 아이디 입니다.' 라고 toast 뜨기
            }
        }
    }

    // 다음 Text가 활성화 되는 함수
    // isNextPage = true 되려면... 중복 아닌 아이디 & 비밀번호칸 차야함.
    private fun setNextToggle(){
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