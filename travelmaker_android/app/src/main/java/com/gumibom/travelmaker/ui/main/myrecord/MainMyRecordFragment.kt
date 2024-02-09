package com.gumibom.travelmaker.ui.main.myrecord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMypageBinding
import com.gumibom.travelmaker.databinding.FragmentMyRecordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMyRecordFragment : Fragment() {

    private var _binding: FragmentMyRecordBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterInit()
        togglePamphlet()
    }

    /**
     * 리싸이클러뷰 초기화 (초기 상태는 여행 중)
     * 여행 중 버튼은 background_button_certification
     * 여행 완료 버튼은 background_button
     */
    private fun setAdapterInit() {

    }

    private fun togglePamphlet() {
        val travelingButton = binding.btnMyRecordTraveling
        val travelFinishButton = binding.btnMyRecordTravelFinish

        binding.btnMyRecordTraveling.setOnClickListener {
            travelingButton.setBackgroundResource(R.drawable.background_button_certification)
            travelFinishButton.setBackgroundResource(R.drawable.background_button)
        }

        binding.btnMyRecordTravelFinish.setOnClickListener {
            travelingButton.setBackgroundResource(R.drawable.background_button)
            travelFinishButton.setBackgroundResource(R.drawable.background_button_certification)
        }
    }

}