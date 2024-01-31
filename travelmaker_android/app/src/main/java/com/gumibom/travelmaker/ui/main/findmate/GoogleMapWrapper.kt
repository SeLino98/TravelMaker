package com.gumibom.travelmaker.ui.main.findmate

import android.Manifest
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gumibom.travelmaker.constant.NOT_ALLOW_PERMISSION
import com.gumibom.travelmaker.util.PermissionChecker
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * @JvmOverloads를 사용하면 생성자를 여러개 만들 필요 없이
 * 여러 오버로딩의 생성자를 만들 수 있다.
 */
private const val TAG = "GoogleMapWrapper_싸피"
class GoogleMapWrapper @JvmOverloads constructor(
    context : Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback{

    private lateinit var mapView : MapView
    private lateinit var googleMap : GoogleMap
    // 위치 정보를 제공하는 API
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationCallback: LocationCallback
    private lateinit var locationPermissionRequest : ActivityResultLauncher<Array<String>>

    private var callback : Callback? = null

    init {
        initView()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        getLatLng()
        requestLocationUpdates()
    }

    fun setCallback(callback : Callback){
        this.callback = callback
    }

    /**
        MapView 초기화, 현재 FrameLayout에 추가
     */
    private fun initView() {
        mapView = MapView(context)
        mapView.onCreate(Bundle())
        mapView.getMapAsync(this)

        addView(mapView)
    }

    /**
     * Google Map에 마커를 추가하고 해당 위치로 카메라 전환
     */
    fun setMarker(location : LatLng, title : String) {
        val zoomLevel = 15.0f // 줌 레벨을 조정하세요. 값이 클수록 더 가까워집니다.

        googleMap.addMarker(MarkerOptions().position(location).title(title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
    }

    /**
     * 내 현재 위치를 마커 없이 파란점만 표시하는 기능
     */
    fun setMyLocation(location : LatLng) {
        val zoomLevel = 15.0f // 줌 레벨을 조정하세요. 값이 클수록 더 가까워집니다.

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
    }

    /**
     * Google Map이 사용 가능할 때 호출되는 콜백
     * Map 객체를 얻어와서 내부 변수에 저장합니다.
     */
    override fun onMapReady(map: GoogleMap?) {
        Log.d(TAG, "onMapReady: 너 호출되니?")
        map?.let { map ->
            googleMap = map
            val uiSettings = googleMap.uiSettings

            // 나의 현재 위치를 파란점으로 표시
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                googleMap.isMyLocationEnabled = true
            }

            // 줌 컨드롤러
            uiSettings.isZoomControlsEnabled = true
            // 내 위치로 이동 버튼 끄기
            uiSettings.isMyLocationButtonEnabled = false

            // 줌 컨트롤러 위치 변경
            googleMap.setPadding(0, 0, 0, 250)

        }
        // 현재 위치 표시 활성화

    }

    /**
     * 현재 위치의 위도 경도를 받아오는 함수
     */
    private fun getLatLng() {
        var hasFineLocationPermission = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        // 위치 권한이 있다면
        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){

            createLocationCallback()
            Log.d(TAG, "getLatLng: 위치권한이 있나?")
        }
        // 위치 권한이 없다면
        else {
//            setLocationPermission()
        }
    }

    /**
     * 위치 정보 콜백 함수를 만드는 함수
     */
    private fun createLocationCallback() {
        Log.d(TAG, "createLocationCallback: 콜백이 왜 안되지?")
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let{
                    Log.d(TAG, "onLocationResult: $it")
                    updateLocation(it.latitude, it.longitude)
                }
            }
        }
    }

    /**
     *  위치 업데이트 요청 함수
     */
    private fun requestLocationUpdates() {
        val locationRequest = LocationRequest()
        locationRequest.interval = TimeUnit.MINUTES.toMillis(1) // 30분마다
        locationRequest.fastestInterval = TimeUnit.MINUTES.toMillis(1)
        locationRequest.smallestDisplacement = 100f // 100m마다
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "requestLocationUpdates: 콜백 함수 ")
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            return
        } else {
            Log.d(TAG, "requestLocationUpdates: 권한이 없나?")
        }

    }

    private fun updateLocation(latitude : Double, longitude : Double) {
        val location = LatLng(latitude, longitude)
        Log.d(TAG, "updateLocation: 너한테 오니?")
        callback?.onLocationUpdated(latitude, longitude)
        Log.d(TAG, "updateLocation: $callback")
    }


    /**
     * GoogleMap의 생명주기를 관리
     */
    fun onResume() {
        mapView.onResume()
    }

    fun onPause() {
        mapView.onPause()
    }

    fun onDestroy() {
        mapView.onDestroy()
    }

    fun onLowMemory() {
        mapView.onLowMemory()
    }
}