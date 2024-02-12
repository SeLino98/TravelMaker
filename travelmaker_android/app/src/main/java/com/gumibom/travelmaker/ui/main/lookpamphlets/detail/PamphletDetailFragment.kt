package com.gumibom.travelmaker.ui.main.lookpamphlets.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.gumibom.travelmaker.constant.NO_RECORD
import com.gumibom.travelmaker.databinding.FragmentPamphletBinding
import com.gumibom.travelmaker.databinding.FragmentPamphletDetailBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.myrecord.detail.MyRecordDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PamphletDetailFragment_싸피"
@AndroidEntryPoint
class PamphletDetailFragment : Fragment() {

    private var _binding: FragmentPamphletDetailBinding? = null
    private val binding get() = _binding!!
    private var pamphletId : Long = 0
    private lateinit var activity: MainActivity
    private lateinit var callback: OnBackPressedCallback

    private lateinit var adapter : PamphletDetailAdapter

    private val myRecordDetailViewModel : MyRecordDetailViewModel by viewModels()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pamphletId = arguments?.getLong("pamphletId") ?: 0

        // OnBackPressedCallback 인스턴스 생성 및 추가
        callback = object : OnBackPressedCallback(true) { // true는 콜백을 활성화 상태로 만듭니다.
            override fun handleOnBackPressed() {
                Log.d(TAG, "handleOnBackPressed: 클릭")
                activity.navigationPop()
                activity.setOriginToolbar()
            }
        }
        // OnBackPressedDispatcher에 콜백 추가
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPamphletDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        observeLiveData()
        eventRecyclerView()
    }

    private fun setAdapter() {
        adapter = PamphletDetailAdapter(requireContext(), myRecordDetailViewModel)
        binding.rvPamphletDetail.adapter = adapter

        myRecordDetailViewModel.getMyAllRecord(pamphletId)
    }

    private fun observeLiveData() {
        myRecordDetailViewModel.myAllRecord.observe(viewLifecycleOwner) { recordList ->
            adapter.submitList(recordList.toMutableList())
        }
    }

    private fun eventRecyclerView() {
        binding.rvPamphletDetail.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                // 아이템이 화면에 나타날 때 호출됩니다.
                Log.d(TAG, "$view")

            }

            override fun onChildViewDetachedFromWindow(view: View) {
                // 아이템이 화면에서 사라질 때 호출됩니다.
                Log.d(TAG, "$view")
                // 여기에서 필요한 작업을 수행하세요.
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}