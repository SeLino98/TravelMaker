package com.gumibom.travelmaker.ui.main.findmate.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.constant.DISPLAY
import com.gumibom.travelmaker.constant.ENGLISH
import com.gumibom.travelmaker.constant.ENGLISH_PATTERN
import com.gumibom.travelmaker.constant.GOOGLE_API_KEY
import com.gumibom.travelmaker.constant.KOREAN
import com.gumibom.travelmaker.constant.KOREAN_PATTERN
import com.gumibom.travelmaker.constant.NAVER_ID_KEY
import com.gumibom.travelmaker.constant.NAVER_SECRET_KEY
import com.gumibom.travelmaker.constant.WRONG_INPUT
import com.gumibom.travelmaker.databinding.FragmentMainFindMateBinding
import com.gumibom.travelmaker.databinding.FragmentMainFindMateSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindMateSearchFragment : Fragment() {

    private var _binding: FragmentMainFindMateSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFindMateSearchBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchPlaces()
    }

    /**
     *  장소 검색
     */
    private fun searchPlaces() {
        // 반짝이는 애니메이션 정의
        val animation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink_animation)

        binding.ivFindMateLocationSearch.setOnClickListener {
            val location = binding.etFindMateSearch.text.toString()

            val language = selectKoreanEnglish(location)

            // 한국어면 네이버 api, 영어면 구글 api 호출
            when (language) {
                KOREAN -> {

                }
                ENGLISH -> {

                }
                else -> {
                    // 둘다 아니면 토스트 메시지 띄움
                    Toast.makeText(requireContext(), language, Toast.LENGTH_SHORT).show()
                }
            }

            // 애니메이션 적용
            binding.ivFindMateLocationSearch.startAnimation(animation)
            // 키보드를 숨기는 함수
            hideKeyboard()
        }
    }

    // 한국어인지 영어인지 구분하는 함수 (정규식 이용)
    private fun selectKoreanEnglish(location : String) : String {
        val koreanRegex = Regex(KOREAN_PATTERN)
        val englishRegex = Regex(ENGLISH_PATTERN)

        // 입력한 장소가 한국어 + 숫자면
        return if (koreanRegex.matches(location)) {
            KOREAN
        }
        // 영어 + 숫자면
        else if (englishRegex.matches(location)) {
            ENGLISH
        }
        // 둘다 아니면
        else {
            WRONG_INPUT
        }
    }

    // 키보드 숨김 함수
    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.etFindMateSearch.clearFocus() // editText의 Focus를 잃게 한다.
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}