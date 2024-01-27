package com.gumibom.travelmaker.ui.login.forgetIdPw

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gumibom.travelmaker.databinding.FragmentLoginFindIdPwBinding
import com.gumibom.travelmaker.databinding.FragmentSignupProfileBinding

class FindIdPwFragment : Fragment() {

    private var _binding : FragmentLoginFindIdPwBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginFindIdPwBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
    }

    private fun setViewPager() {
        val adapter = FindIdPwViewPagerAdapter(this)
        val viewPager = binding.viewpagerLoginFindId
        val tabLayout = binding.tabLoginFindId

        viewPager.adapter = adapter

        val tabLayoutTextArray = arrayOf("아이디 찾기","비밀번호 재생성")

        //TablayoutMediator로 탭 레이아웃과 뷰페이저를 연결 한다. 이때 탭 아이템도 같이 생성된다.
        TabLayoutMediator(tabLayout, viewPager){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}