package com.gumibom.travelmaker.ui.main.mygroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentMainMyGroupBinding
import com.gumibom.travelmaker.databinding.FragmentStartPamphletBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMyGroupFragment : Fragment() {
    private var _binding :FragmentMainMyGroupBinding? = null
    private val binding = _binding!!;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMyGroupBinding.inflate(inflater,container,false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}