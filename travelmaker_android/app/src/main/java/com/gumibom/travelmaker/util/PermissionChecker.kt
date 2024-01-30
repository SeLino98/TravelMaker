package com.gumibom.travelmaker.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint

class PermissionChecker (private val context : Context){
    lateinit var permitted : PermissionListener
    fun checkPermission(permission: Array<String>) : Boolean{
        //내가 받은 권한들을 리스트로 받고 하나라도 거부된 권한이 있다면 false를 리턴한다.
        for (permission in permission){
            if (ActivityCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkPermission() {
        val checker = PermissionChecker(context)
        val runtimePermissions = arrayOf(
            Manifest.permission.POST_NOTIFICATIONS
        )
        if (!checker.checkPermission(runtimePermissions)) {
            checker.requestPermissionLauncher.launch(runtimePermissions)
            checker.permitted = object : PermissionListener {
                override fun onGranted() {
                    //퍼미션 획득 성공일때
                }
            }
        } else {

        }
    }

    val locationPermissionRequest = (context as AppCompatActivity).registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
            } else -> {
            // No location access granted.
                moveToSettings()
            }
        }
    }



    //권한 호출 후 결과받아 처리할 런처이다 권한 없는 것을 확인하고 다른 창이나 설정으로 이동시킨다.
    //startPermiossionRequestResult
    val requestPermissionLauncher = (context as AppCompatActivity).registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()){
        if (it.values.contains(false)){
            Toast.makeText(context,"권한이 부족합니다 설정창으로 넘어갑니다.",Toast.LENGTH_SHORT).show()
            moveToSettings()
        }else{
            Toast.makeText(context,"모든 권한이 허가됐습니다. ",Toast.LENGTH_SHORT).show()
            permitted.onGranted()
        }
    }
    //사용자가 권한을 허용하지 않았을때, 설정창으로 이동
    fun moveToSettings() { // TODO:  내 커스텀 다이얼로그로 수정
        val alertDialog = AlertDialog.Builder( context )
        alertDialog.setTitle("권한이 필요합니다.")
        alertDialog.setMessage("설정으로 이동합니다.")
        alertDialog.setPositiveButton("확인") { dialogInterface, i -> // 안드로이드 버전에 따라 다를 수 있음.
            val intent =
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + context.packageName))
            context.startActivity(intent)
            dialogInterface.cancel()
        }
        alertDialog.setNegativeButton("취소") { dialogInterface, i -> dialogInterface.cancel() }
        alertDialog.show()
    }

}