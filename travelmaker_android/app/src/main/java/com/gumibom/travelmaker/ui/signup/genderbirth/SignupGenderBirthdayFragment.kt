package com.gumibom.travelmaker.ui.signup.genderbirth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupGenderBirthdayBinding
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import com.gumibom.travelmaker.ui.signup.idpw.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class SignupGenderBirthdayFragment : Fragment(){
    private lateinit var activity: SignupActivity;
    private val signupViewModel: SignupViewModel by viewModels()
    private var _binding:FragmentSignupGenderBirthdayBinding? = null
    private val binding get() = _binding!!
    private var isNextPage = false
    private var isGenderSelected = false
    private var isBirthSelected = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupGenderBirthdayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isBirthSelected = true
        // Z. 이전/다음 버튼 활성화
        updateNextBtnState()
        // A. 성별 버튼의 한쪽 선택하여 클릭시, 선택된 쪽의 색이 좀더 진해짐.
        setGenderBtnColorToggle()
        // B. 스피너로 생년월일의 연/월/일 선택 <- default 값, 시작~끝 범위 정해줘야 함
        setDatepicker()
        // C. A,B 모두 제대로 선택시 <- 다음 버튼이 진해지고, isNextPage = true 됨

        // D.

    }

    /*
    setGenderBtnColorToggle(){}
    manBtn 누르면 manBtn이 진해지고, womanBtn 누르면 wonmanBtn이 진해짐.
    */
    private fun setGenderBtnColorToggle() {
        val manBtn = binding.btnSignupMan
        val womanBtn = binding.btnSignupWoman
        val nonActiveColor = ContextCompat.getColor(requireContext(), R.color.light_gray)
        val activeColor = ContextCompat.getColor(requireContext(), R.color.blue_gray)

        // 만약 manBtn이 클릭된 경우
        manBtn.setOnClickListener {
            manBtn.setBackgroundColor(activeColor)
            womanBtn.setBackgroundColor(nonActiveColor)
            isGenderSelected = true
            checkNextPageEligibility()
        }
        // 만약 womanBtn이 클릭된 경우
        womanBtn.setOnClickListener {
            manBtn.setBackgroundColor(nonActiveColor)
            womanBtn.setBackgroundColor(activeColor)
            isGenderSelected = true
            checkNextPageEligibility()
        }

    }
    /*
    setDatepicker()
    */
    private fun setDatepicker() {
        val datepicker = binding.dpSignupBirthSpinner
        val maxCalendar = Calendar.getInstance()
        val minCalendar = Calendar.getInstance()

        datepicker.init(1994, 11, 10, DatePicker.OnDateChangedListener {
                    view, year, monthOfYear, dayOfMonth ->
                Log.d(TAG, "선택된 날짜: $year-${monthOfYear + 1}-$dayOfMonth")
            isBirthSelected = true
            checkNextPageEligibility()
        })
        maxCalendar.set(2008, Calendar.JANUARY, 1)
        minCalendar.set(1924, Calendar.JANUARY, 1)
        datepicker.maxDate = maxCalendar.timeInMillis
        datepicker.minDate = minCalendar.timeInMillis
    }

    /*
    setNextToggle()
    */
    // 성별 선택과 생일 선택이 모두 이뤄졌는지 체크하는 함수.
    private fun checkNextPageEligibility() {
        isNextPage = isGenderSelected && isBirthSelected
        updateNextBtnState()
    }
    // '다음'버튼의 활성화 상태를 업데이트하는 함수
    private fun updateNextBtnState() {
        binding.btnSignup4Next.isEnabled = isNextPage
        if (isNextPage) {
            binding.btnSignup4Next.setOnClickListener{
                activity.navigateToNextFragment()
            }
        } else {
            binding.btnSignup4Next.setOnClickListener(null)
        }
    }
    /*
    onDestroyView()
    */
    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰 파괴시 메모리 누수를 방지하기 위해 _binding = null
        _binding= null
    }

}