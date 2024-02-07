package com.gumibom.travelmaker.ui.main.notification

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainNotificationBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.main.MainViewModel
import com.gumibom.travelmaker.util.SharedPreferencesUtil

class MainNotificationFragment : Fragment() {
    private var _binding :FragmentMainNotificationBinding? = null
    private val binding = _binding!!
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

        viewModel.getNotifyList(8)

        observeNotify()
    }
    fun observeNotify(){
        viewModel.isGetNotifyList.observe(viewLifecycleOwner){
            //어뎁터 그리기

        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainNotificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}