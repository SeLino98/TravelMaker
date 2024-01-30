package com.gumibom.travelmaker.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity_SSAFY"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: @#!@#!@#")
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_main)
                    as NavHostFragment).navController
        }
        setContentView(binding.root)
        initToolbar()
    }
    fun testMoveFragment() {
        navController.navigate(R.id.action_mainFindMateFragment_to_mainFindMateDetailFragment)
    }
    fun navigationToNextFragment(){
        navController.navigate(R.id.action_mainFragment_to_mainFindMateFragment)
    }
    private fun initToolbar(){
        binding.toolbar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_notify -> {
                    Log.d(TAG, "initToolbar: Noyigiyu")
                    true
                }
                R.id.action_search -> {
                    Log.d(TAG, "initToolbar:SEARYCDGC")
                    true
                }
                R.id.action_my_page -> {
                    Log.d(TAG, "initToolbar:_inho!")
                    true
                }
                else -> false
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null //메모리 누수 방지
    }
}
