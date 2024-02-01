package com.gumibom.travelmaker.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityMainBinding
import com.gumibom.travelmaker.util.PermissionChecker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_싸피"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: @#!@#!@#")
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_main)
                    as NavHostFragment).navController
        }
        setContentView(binding.root)
        initToolbar()
        setFirebase()
        setNavigationMenuToolbar()
    }
    private fun setNavigationMenuToolbar(){
        //프래그먼트가 ~~ 일 땐 ~~로
        //프래그먼트가 ㅌㅌ 일 땐 ㅌㅌ 로
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> {
                    binding.toolbar.menu.clear() // 기존 메뉴 제거
                    binding.toolbar.inflateMenu(R.menu.menu_main) // 새 메뉴 설정
                    binding.toolbar.title = "TEST"
                    binding.toolbar.navigationIcon = null
                }
                R.id.mainMyGroupFragment, R.id.mainFindMateFragment -> {
                    binding.toolbar.menu.clear() // 기존 메뉴 제거
                    binding.toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_24)
                    binding.toolbar.title = "HHHH"
                    binding.toolbar.setNavigationOnClickListener {
                        Log.d(TAG, "setNavigationMenuToolbar: ")
                        navController.navigateUp()
                    }
                    binding.toolbar.inflateMenu(R.menu.detail_menu_main) // 새 메뉴 설정
                }
            }
        }
    }

    private fun setFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }
            // token log 남기기
            Log.d(TAG, "token: ${task.result?:"task.result is null"}")
            if(task.result != null){
                uploadToken(task.result!!)
            }
        })
        createNotificationChannel(CHANNEL_ID, "travelmaker")
    }

    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
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

    companion object {
        // Notification Channel ID
        const val CHANNEL_ID = "travelmaker_channel"

        // TODO 서버에 토큰 업로드
        fun uploadToken(token: String) {
            Log.d(TAG, "uploadToken: $token")
        }
    }
}
