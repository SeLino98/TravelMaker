package com.gumibom.travelmaker.ui.main.myrecord.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.constant.NO_RECORD
import com.gumibom.travelmaker.databinding.FragmentMyRecordBinding
import com.gumibom.travelmaker.databinding.FragmentMyRecordDetailBinding
import com.gumibom.travelmaker.ui.dialog.ClickEventDialog
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.findmate.meeting_post.MeetingPostActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MyRecordDetail_싸피"
@AndroidEntryPoint
class MyRecordDetail : Fragment() {

    private var _binding: FragmentMyRecordDetailBinding? = null
    private val binding get() = _binding!!
    private val myRecordDetailViewModel : MyRecordDetailViewModel by viewModels()
    private lateinit var activity: MainActivity
    private var pamphletId : Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

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
        observeLiveData()
        createMyRecord()
    }

    /**
     * 아이콘 클릭 시 다이얼로그 생성
     * 사진+텍스트 또는 영상을 업로드 할 수 있게하는 페이지 이동
     */
    private fun createMyRecord() {
        binding.ivMyRecordDetailEdit.setOnClickListener {
            Log.d(TAG, "createMyRecord: 호출되니")

            val alertDialog = ClickEventDialog(requireContext())
            val bundle = bundleOf("pamphletId" to pamphletId)

            alertDialog.setIgnoreTitleContent()
            alertDialog.setPositiveBtnTitle("사진 + 텍스트를 만들어보세요")
            alertDialog.setNegativeBtnTitle("영상을 만들어보세요.")

            alertDialog.setPositiveButtonListener {
                // 사진 + 텍스트 화면으로 이동
                Navigation.findNavController(it).navigate(R.id.action_myRecordDetail_to_makeMyRecordPictureFragment, bundle)
            }

            alertDialog.setNegativeButtonListener {
                // 영상 화면으로 이동
                Navigation.findNavController(it).navigate(R.id.action_myRecordDetail_to_makeMyRecordVideoFragment, bundle)
            }
            alertDialog.clickDialogShow()
        }
    }

    private fun observeLiveData() {
        myRecordDetailViewModel.myAllRecord.observe(viewLifecycleOwner) { recordList ->
            if (recordList.isEmpty()) {
                binding.tvMyRecordDetailText.text = NO_RECORD
            }
        }
    }

    /**
     * 초기 세팅
     */
    private fun setInit() {

        myRecordDetailViewModel.getMyAllRecord(pamphletId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}