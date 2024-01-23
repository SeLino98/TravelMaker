package com.gumibom.travelmaker.ui.signup.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.constant.DISPLAY
import com.gumibom.travelmaker.constant.NAVER_ID_KEY
import com.gumibom.travelmaker.constant.NAVER_SECRET_KEY
import com.gumibom.travelmaker.constant.NO_SEARCH_LOCATION
import com.gumibom.travelmaker.databinding.FragmentSignupLocationBinding
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "SignupLocationFragment_싸피"
@AndroidEntryPoint
class SignupLocationFragment : Fragment() {

    private var _binding : FragmentSignupLocationBinding? = null
    private val binding get() = _binding!!

    private val signupViewModel : SignupViewModel by activityViewModels()

    private lateinit var adapter : SignupLocationAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        searchLocation()
        setItemClick()
    }

    private fun setItemClick() {

    }

    // 리싸이클러뷰 세팅
    private fun setAdapter() {
        adapter = SignupLocationAdapter(requireContext(), signupViewModel)

        signupViewModel.naverAddressList.observe(viewLifecycleOwner) { addressList ->
            Log.d(TAG, "setAdapter: $addressList")

            if (addressList.isNotEmpty()) {
                adapter.submitList(addressList.toMutableList())
            } else {
                adapter.submitList(addressList.toMutableList())
                Toast.makeText(requireContext(), NO_SEARCH_LOCATION, Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvLocationList.adapter = adapter
    }
    // EditText에 위치 검색
    private fun searchLocation() {
        // 반짝이는 애니메이션 정의
        val animation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink_animation)

        binding.ivSignupLocationSearch.setOnClickListener {
            val location = binding.etSignupLocation.text.toString()
            signupViewModel.getNaverLocation(NAVER_ID_KEY, NAVER_SECRET_KEY, location, DISPLAY)

            // 애니메이션 적용
            binding.ivSignupLocationSearch.startAnimation(animation)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}