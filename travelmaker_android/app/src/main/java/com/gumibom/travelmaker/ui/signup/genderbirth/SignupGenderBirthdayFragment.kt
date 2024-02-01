package com.gumibom.travelmaker.ui.signup.genderbirth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupGenderBirthdayBinding
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import com.gumibom.travelmaker.ui.signup.idpw.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupGenderBirthdayFragment : Fragment(){
    private lateinit var activity: SignupActivity;
    private val signupViewModel: SignupViewModel by viewModels()
    private var _binding:FragmentSignupGenderBirthdayBinding? = null
    private val binding get() = _binding!!
    private val isNextPage = false
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as SignupActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupGenderBirthdayBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backAndNextNaviBtn()
    }
    private fun backAndNextNaviBtn(){
        val btnSignupPrevious = binding.btnSignup4Previous
            btnSignupPrevious.setOnClickListener {
            activity.navigateToPreviousFragment()
        }
        val btnSignupNext = binding.btnSignup4Next
            btnSignupNext.setOnClickListener {
            activity.navigateToNextFragment()
        }
    }
    private fun setNextToggle(){
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)

        signupViewModel.isDuplicatedId.observe(viewLifecycleOwner) {it ->
            Log.d(TAG, "setNextToggle: ")
            if (it == false){
                // 이전 버튼의 색을 activeColor 로 변경하고, isNextPage 값을 true로 변경
                binding.btnSignup4Next.setTextColor(activeColor)
                isNextPage = true
                // 중복된 아이디가 맞는 경우
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

}