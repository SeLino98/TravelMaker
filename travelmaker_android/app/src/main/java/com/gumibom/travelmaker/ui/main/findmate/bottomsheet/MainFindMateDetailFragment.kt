package com.gumibom.travelmaker.ui.main.findmate.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.data.dto.request.FcmRequestGroupDTO
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.FragmentMainFindMateDetailBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.MainViewModel
import com.gumibom.travelmaker.util.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFindMateDetailFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentMainFindMateDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity : MainActivity
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferencesUtil

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
        sharedPreferences = SharedPreferencesUtil(activity)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterView()
        setApplyGroup()
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFindMateDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun observeLiveData(){
        viewModel.isRequestSuccess.observe(viewLifecycleOwner){
            if (it.isSuccess){
                Log.d(TAG, "observeLiveData: ${it.isSuccess.toString()}")
                Log.d(TAG, "observeLiveData: ${it.message}")
            }else{
                Log.d(TAG, "observeLiveData: ${it.message}")
            }
        }
    }

    private fun setAdapterView(){

    }
    private fun setApplyGroup(){
        binding.btnApplyGroup.setOnClickListener {
            //firebase 연동하기
            Log.d(TAG, "setApplyGroup: 1")
            //그룹 id랑 현재 로그인 한 유저 id 전송하기
            val userid = sharedPreferences.getUser().userId.toString()
            viewModel.requestGroup(FcmRequestGroupDTO(userid,5))
            Log.d(TAG, "setApplyGroup: 2")
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}
