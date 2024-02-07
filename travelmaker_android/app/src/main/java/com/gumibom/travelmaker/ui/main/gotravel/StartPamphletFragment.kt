package com.gumibom.travelmaker.ui.main.gotravel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.gumibom.travelmaker.databinding.FragmentMakePamphletBinding
import com.gumibom.travelmaker.databinding.FragmentStartPamphletBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "StartPamphletFragment_싸피"
@AndroidEntryPoint
class StartPamphletFragment : Fragment() {

    private var _binding: FragmentStartPamphletBinding? = null
    private val binding get() = _binding!!
    private val makePamphletViewModel : MakePamphletViewModel by viewModels()
    private lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = arguments?.getString("pamphlet_title") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartPamphletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectCategory()
        makePamphlet()
        Log.d(TAG, "onViewCreated: $title")
    }

    /**
     * 팜플렛 카테고리를 선택하는 함수
     */
    private fun selectCategory() {

        val chipMap = mapOf<String, String>("맛집" to "taste", "힐링" to "healing", "문화" to "culture", "활동" to "active",
            "사진" to "picture", "자연" to "nature", "쇼핑" to "shopping", "휴식" to "rest")

        val chipGroup1 = binding.chipGroup1
        val chipGroup2 = binding.chipGroup2

        for (index in 0 until chipGroup1.childCount) {
            val chip = chipGroup1.getChildAt(index) as? Chip
            chip?.setOnClickListener {
                val chipText = chipMap.getValue(chip.text.toString())
                // Chip 클릭 시 실행할 코드
                if (chip.isChecked) {
                    makePamphletViewModel.categoryList.add(chipText)
                    Log.d(TAG, "selectCategory: ${makePamphletViewModel.categoryList}")
                } else {
                    makePamphletViewModel.categoryList.remove(chipText)
                }
            }
        }

        for (index in 0 until chipGroup2.childCount) {
            val chip = chipGroup2.getChildAt(index) as? Chip
            chip?.setOnClickListener {
                val chipText = chipMap.getValue(chip.text.toString())
                // Chip 클릭 시 실행할 코드
                if (chip.isChecked) {
                    makePamphletViewModel.categoryList.add(chipText)
                } else {
                    makePamphletViewModel.categoryList.remove(chipText)
                }
            }
        }
    }

    /**
     * 팜플렛을 만드는 함수
     */
    private fun makePamphlet() {

    }
}