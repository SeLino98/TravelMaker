package com.gumibom.travelmaker.ui.main.myrecord

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMypageBinding
import com.gumibom.travelmaker.databinding.FragmentMyRecordBinding
import com.gumibom.travelmaker.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainMyRecordFragment_싸피"
@AndroidEntryPoint
class MainMyRecordFragment : Fragment() {

    private var _binding: FragmentMyRecordBinding? = null
    private val binding get() = _binding!!

    private val myRecordViewModel : MyRecordViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter : MyRecordAdapter

    private var isFinish = false
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
        Log.d(TAG, "setAdapterInit: 호출됨")
        adapter = MyRecordAdapter(requireContext())
        binding.rvMyRecord.adapter = adapter

        myRecordViewModel.getMyRecord(mainViewModel.user.value!!.userId)

        myRecordViewModel.myRecordIng.observe(viewLifecycleOwner) { pamphletList ->
            if (!isFinish) {
                adapter.submitList(pamphletList.toMutableList())
            }

        }

        myRecordViewModel.myRecordFinish.observe(viewLifecycleOwner) { pamphletList ->
            if (isFinish) {
                adapter.submitList(pamphletList.toMutableList())
            }
        }
    }

    private fun togglePamphlet() {
        val travelingButton = binding.btnMyRecordTraveling
        val travelFinishButton = binding.btnMyRecordTravelFinish

        binding.btnMyRecordTraveling.setOnClickListener {
            travelingButton.setBackgroundResource(R.drawable.background_button_certification)
            travelFinishButton.setBackgroundResource(R.drawable.background_button)

            isFinish = false
            setAdapterInit()
        }

        binding.btnMyRecordTravelFinish.setOnClickListener {
            travelingButton.setBackgroundResource(R.drawable.background_button)
            travelFinishButton.setBackgroundResource(R.drawable.background_button_certification)

            isFinish = true
            setAdapterInit()
        }
    }

}