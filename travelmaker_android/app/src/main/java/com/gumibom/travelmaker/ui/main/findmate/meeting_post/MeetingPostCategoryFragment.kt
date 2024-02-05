package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.gumibom.travelmaker.databinding.FragmentMeetingPostCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MeetingPostCategoryFrag_싸피"
@AndroidEntryPoint
class MeetingPostCategoryFragment : Fragment() {

    private var _binding: FragmentMeetingPostCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MeetingPostActivity

    private val meetingPostViewModel : MeetingPostViewModel by activityViewModels()

    private lateinit var spinnerMaxMember : Spinner
    private lateinit var spinnerMinTraveler : Spinner
    private lateinit var spinnerMinNative : Spinner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MeetingPostActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeetingPostCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerMaxMember = binding.spinnerMaxMember
        spinnerMinTraveler = binding.spinnerMinTraveler
        spinnerMinNative = binding.spinnerMinNative

        setSpinner()
        selectCategory()
        createMeeting()
    }

    /**
     * 스피너를 설정하는 함수
     */
    private fun setSpinner() {
        val items = arrayOf("선택", "1", "2", "3", "4", "5", "6")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerMaxMember.adapter = adapter
        spinnerMinTraveler.adapter = adapter
        spinnerMinNative.adapter = adapter

        spinnerMaxMember.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 기본 형태는 toString()으로 치환되므로 toString() -> toInt() 작업 필요
                val value = parent.getItemAtPosition(position).toString()

                if (!isInitSpinner(value)) {
                    meetingPostViewModel.maxMember = value.toInt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 항목도 선택되지 않았을 때 실행할 코드
            }
        }

        spinnerMinTraveler.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 기본 형태는 toString()으로 치환되므로 toString() -> toInt() 작업 필요
                val value = parent.getItemAtPosition(position).toString()

                if (!isInitSpinner(value)) {
                    meetingPostViewModel.minTraveler = value.toInt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 항목도 선택되지 않았을 때 실행할 코드
            }
        }

        spinnerMinNative.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 기본 형태는 toString()으로 치환되므로 toString() -> toInt() 작업 필요
                val value = parent.getItemAtPosition(position).toString()

                if (!isInitSpinner(value)) {
                    meetingPostViewModel.minNative = value.toInt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 항목도 선택되지 않았을 때 실행할 코드
            }
        }

    }

    private fun isInitSpinner(value : String) : Boolean {
        if (value == "선택") {
            return true
        }
        return false
    }

    /**
     * 카테고리를 설정하는 함수
     */
    private fun selectCategory() {

        val chipMap = mapOf<String, String>("맛집" to "taste", "힐링" to "healing", "문화" to "culture", "활동" to "active",
                                            "사진" to "picture", "자연" to "nature", "쇼핑" to "shopping", "휴식" to "rest")

        val chipGroup1 = binding.chipGroup1

        for (index in 0 until chipGroup1.childCount) {
            val chip = chipGroup1.getChildAt(index) as? Chip
            chip?.setOnClickListener {
                // Chip 클릭 시 실행할 코드
                if (chip.isChecked) {
                    val chipText = chipMap.getValue(chip.text.toString())
                    meetingPostViewModel.categoryList.add(chipText)
                }
            }
        }
    }

    private fun createMeeting() {

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}