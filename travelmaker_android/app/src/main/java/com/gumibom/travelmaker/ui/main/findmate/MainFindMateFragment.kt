package com.gumibom.travelmaker.ui.main.findmate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.FragmentMainFindMateBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.util.PermissionChecker
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

private const val TAG = "MainFindMateFragment"
@AndroidEntryPoint
class MainFindMateFragment : Fragment() {
    private var _binding:FragmentMainFindMateBinding? = null
    private val binding get() = _binding!!
    val bottomsheetFragment = MainFindMateDetailFragment()
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var activity : MainActivity
    private lateinit var googleMapWrapper : GoogleMapWrapper

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
        val standardBottomSheet = binding.bts.bottomSheetLayout
        val standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)
        standardBottomSheetBehavior.state = STATE_HALF_EXPANDED;
        standardBottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {


            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val screenHeight = Resources.getSystem().displayMetrics.heightPixels
                val halfHeight = screenHeight / 2
                val currentTop = screenHeight - bottomSheet.top
                val bottomToHalfSize = halfHeight+0/2;
                val halfToTop = (halfHeight+screenHeight)/2;
                when (currentTop) {
                    in 0 until bottomToHalfSize -> standardBottomSheetBehavior.state = STATE_COLLAPSED
                    in bottomToHalfSize until halfToTop -> standardBottomSheetBehavior.state = STATE_HALF_EXPANDED
                    in halfToTop  until  screenHeight -> standardBottomSheetBehavior.state = STATE_EXPANDED
                }
                Log.d(TAG, "onSlide:_ ${screenHeight}-${halfHeight}:${currentTop}-${bottomToHalfSize}-${halfToTop} : PKEEKEKEKEK")

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        })

        googleMapWrapper = GoogleMapWrapper(requireContext())
        binding.googleMap.addView(googleMapWrapper)
    }


    // Set initial peek height


    // Assuming you have a method to convert dp to pixels
    fun Int.dpToPixels(context: Context): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
    ).toInt()

    private fun modalBottomSheetEvent(){
//        val bottomSheetBackCallback = object : OnBackPressedCallback(/* enabled= */false) {
//            override fun handleOnBackStarted(backEvent: BackEventCompat) {
//                bottomSheetBehavior.startBackProgress(backEvent)
//            }
//
//            override fun handleOnBackProgressed(backEvent: BackEventCompat) {
//                bottomSheetBehavior.updateBackProgress(backEvent)
//            }
//
//            override fun handleOnBackPressed() {
//                bottomSheetBehavior.handleBackInvoked()
//            }
//
//            override fun handleOnBackCancelled() {
//                bottomSheetBehavior.cancelBackProgress()
//            }
//        }
    }
    override fun onResume() {
        super.onResume()
        googleMapWrapper.onResume()
    }

    override fun onPause() {
        super.onPause()
        googleMapWrapper.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        googleMapWrapper.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        googleMapWrapper.onLowMemory()
    }
}



/*
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
*/
