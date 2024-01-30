package com.gumibom.travelmaker.ui.main.findmate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.FragmentMainFindMateBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFindMateFragment : Fragment() {
    private var _binding:FragmentMainFindMateBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var activity : MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFindMateBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Assuming the Bottom Sheet is part of the Fragment's layout
        bottomSheetBehavior = BottomSheetBehavior.from(binding.persistentBottomSheet)

        // Initially hide the Bottom Sheet
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        persistentBottomSheetEvent()
    }
    private fun persistentBottomSheetEvent() {
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED-> {

                    }
                    BottomSheetBehavior.STATE_DRAGGING-> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        activity.testMoveFragment()
                    }
                    BottomSheetBehavior.STATE_HIDDEN-> {

                    }
                    BottomSheetBehavior.STATE_SETTLING-> {

                    }
                }
            }
        })
    }


}