package com.gumibom.travelmaker.ui.main.lookpamphlets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMyRecordBinding
import com.gumibom.travelmaker.databinding.FragmentPamphletBinding
import com.gumibom.travelmaker.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainLookPamphletsFragment : Fragment() {

    private var _binding: FragmentPamphletBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel : MainViewModel by activityViewModels()
    private val lookPamphletViewModel : LookPamphletViewModel by viewModels()
    private var userId : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPamphletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInit()
        setAdapter()
    }

    private fun setInit() {
        userId = mainViewModel.user.value!!.userId
        lookPamphletViewModel.getOtherPamphletList(userId)
    }

    /**
     * 리싸이클러뷰 세팅
     */
    private fun setAdapter() {
        val adapter = LookPamphletAdapter(requireContext())
        binding.rvPamphlet.adapter = adapter

        lookPamphletViewModel.otherPamphlet.observe(viewLifecycleOwner) { pamphletList ->
            adapter.submitList(pamphletList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}