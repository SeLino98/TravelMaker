package com.gumibom.travelmaker.ui.main.findmate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
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
import com.gumibom.travelmaker.util.PermissionChecker
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * @JvmOverloads를 사용하면 생성자를 여러개 만들 필요 없이
 * 여러 오버로딩의 생성자를 만들 수 있다.
 */
class GoogleMapWrapper @JvmOverloads constructor(
    context : Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback{

    private lateinit var mapView : MapView
    private lateinit var googleMap : GoogleMap
    // 위치 정보를 제공하는 API
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var permissionChecker: PermissionChecker

    private lateinit var locationCallback: LocationCallback


    init {
        initView()
        permissionChecker = PermissionChecker(context) // 퍼미션 체커 객체 생성
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        getLatLng()
        requestLocationUpdates()
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
        googleMap.addMarker(MarkerOptions().position(location).title(title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    /**
     * Google Map이 사용 가능할 때 호출되는 콜백
     * Map 객체를 얻어와서 내부 변수에 저장합니다.
     */
    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
        }
    }

    /**
     * 위치 권한을 받아오는 함수
     */
    private fun setLocationPermission() {
        permissionChecker.locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    /**
     * 현재 위치의 위도 경도를 받아오는 함수
     */
    private fun getLatLng() {
        var currentLatLng : Location? = null

        var hasFineLocationPermission = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        // 위치 권한이 있다면
        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){

            createLocationCallback()
        }
        // 위치 권한이 없다면
        else {
            setLocationPermission()
        }
    }

    /**
     * 위치 정보 콜백 함수를 만드는 함수
     */
    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let{
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
        locationRequest.interval = TimeUnit.MINUTES.toMillis(30) // 30분마다
        locationRequest.fastestInterval = TimeUnit.MINUTES.toMillis(30)
        locationRequest.smallestDisplacement = 100f // 100m마다
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            return
        }

    }

    private fun updateLocation(latitude : Double, longitude : Double) {
        val location = LatLng(latitude, longitude)
        setMarker(location, "현재 위치")
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