package com.gumibom.travelmaker.ui.main.myrecord.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentMyRecordBinding
import com.gumibom.travelmaker.databinding.FragmentMyRecordDetailBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MyRecordDetail_싸피"
@AndroidEntryPoint
class MyRecordDetail : Fragment() {

    private var _binding: FragmentMyRecordDetailBinding? = null
    private val binding get() = _binding!!
    
    private var pamphletId : Long = 0 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pamphletId = arguments?.getLong("pamphletId") ?: 0
        Log.d(TAG, "onCreate: $pamphletId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyRecordDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInit()
    }

    /**
     * 초기 세팅
     */
    private fun setInit() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}