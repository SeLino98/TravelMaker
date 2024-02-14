package com.gumibom.travelmaker.ui.login.forgetIdPw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        checkMyId()
    }

    /**
     * 아이디 확인 하기 눌렀을 때 아이디 찾기
     */
    private fun checkMyId() {
        binding.btnLoginFindIdId.setOnClickListener {
            val phoneNumber = binding.tieLoginFindIdPhone.text.toString()

            if (phoneNumber.isNotEmpty()) {
                Toast.makeText(requireContext(), "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {

            }
        }
    }
}