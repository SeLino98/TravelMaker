package com.gumibom.travelmaker.ui.main.gotravel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentMakePamphletBinding
import com.gumibom.travelmaker.databinding.FragmentStartPamphletBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "StartPamphletFragment_싸피"

@AndroidEntryPoint
class StartPamphletFragment : Fragment() {

    private var _binding: FragmentStartPamphletBinding? = null
    private val binding get() = _binding!!
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
        Log.d(TAG, "onViewCreated: $title")
    }

    /**
     * 팜플렛 카테고리를 선택하는 함수
     */
    private fun selectCategory() {

    }
}