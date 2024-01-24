package com.gumibom.travelmaker.ui.signup.phone

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.gumibom.travelmaker.databinding.FragmentSignupPhoneBinding

class SignupPhoneFragment : Fragment() {

    private var _binding : FragmentSignupPhoneBinding? = null
    private val binding get() = _binding!!

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



    private fun clearFocusAndHideKeyboard(editText: EditText) {
        editText.clearFocus()
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}