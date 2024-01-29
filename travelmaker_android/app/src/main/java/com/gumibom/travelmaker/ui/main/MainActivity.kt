package com.gumibom.travelmaker.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding

private const val TAG = "MainActivity_SSAFY"
class MainActivity : AppCompatActivity() {
    private var _binding :ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: @#!@#!@#")
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
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
}
