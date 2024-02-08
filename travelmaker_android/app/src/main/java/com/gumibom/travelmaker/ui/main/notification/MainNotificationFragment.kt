package com.gumibom.travelmaker.ui.main.notification

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.data.dto.request.FcmGetNotifyListDTO
import com.gumibom.travelmaker.databinding.FragmentMainNotificationBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.MainViewModel
import com.gumibom.travelmaker.util.SharedPreferencesUtil
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

private const val TAG = "MainNotificationFragmen"
@AndroidEntryPoint
class MainNotificationFragment : Fragment() {
    private var _binding :FragmentMainNotificationBinding? = null
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

        viewModel.getNotifyList(1)

        observeLiveData()

//      setupTabLayout()

    }
    private fun observeLiveData(){
        var getNotifyList : FcmGetNotifyListDTO? = null;
        viewModel.isGetNotifyList.observe(viewLifecycleOwner){
            if (it!=null){
                Log.d(TAG, "observeLiveData: GDGD")
                getNotifyList = it
                Log.d(TAG, "observeLiveData: ${getNotifyList.toString()}")
            }
        }

    }
    private fun updateAdapterForTabOne(){
//        binding.rcNotifyList.adapter = mainFcmNotifyAdapter

    }
    private fun updateAdapterForTabTwo(){
//        mainFcmNotifyAdapter.submitList()
//        binding.rcNotifyList.adapter = mainFcmNotifyAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainNotificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}