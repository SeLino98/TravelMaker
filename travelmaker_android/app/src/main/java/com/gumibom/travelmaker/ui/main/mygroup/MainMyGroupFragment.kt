package com.gumibom.travelmaker.ui.main.mygroup

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMyGroupBinding
import com.gumibom.travelmaker.databinding.FragmentStartPamphletBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.MainViewModel
import com.gumibom.travelmaker.util.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


private const val TAG = "MainMyGroupFragment"
@AndroidEntryPoint
class MainMyGroupFragment : Fragment() {
    private var _binding :FragmentMainMyGroupBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity :MainActivity
    private val viewModel:MainViewModel by activityViewModels()
    private lateinit var sharedPreferencesUtil: SharedPreferencesUtil
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onCreate: 1")
        activity = context as MainActivity
        sharedPreferencesUtil = SharedPreferencesUtil(activity)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //여기서ㅂㅈㄷㄷㅂㄷㅈㅂ ㅈㄱ ㄷㅈㅂ가 ㅓㅈㅂ기ㅏ ㅅ더ㅏ지
        //서버에 요청을 날린다.
        //데이터를 받아왔다면? 그룹 리사이클러 뷰에 뿌려준다.
        //Diff 파일과 recycler 파일 둘 다 만들어야 된다.
        //데이터 받는 viewmodel로직을 짜야된다.


        Log.d(TAG, "onViewCreated: ")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMyGroupBinding.inflate(inflater,container,false);
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }
}