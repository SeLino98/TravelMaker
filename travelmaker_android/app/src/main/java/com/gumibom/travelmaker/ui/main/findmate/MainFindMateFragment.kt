package com.gumibom.travelmaker.ui.main.findmate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.FragmentMainFindMateBinding

class MainFindMateFragment : Fragment() {
    private var _binding:FragmentMainFindMateBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity : ActivityMainBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ActivityMainBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFindMateBinding.inflate(inflater,container,false);
        return binding.root
    }
}