package com.gumibom.travelmaker.ui.signup.nickname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentSignupNicknameBinding
import com.gumibom.travelmaker.ui.signup.SignupActivity

class SignupNicknameFragment : Fragment(){
    private var _binding :FragmentSignupNicknameBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity : SignupActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as SignupActivity

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
    }
}