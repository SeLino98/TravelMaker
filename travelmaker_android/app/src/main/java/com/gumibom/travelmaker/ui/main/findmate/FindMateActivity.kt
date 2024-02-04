package com.gumibom.travelmaker.ui.main.findmate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.chip.Chip
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.constant.DENIED_LOCATION_PERMISSION
import com.gumibom.travelmaker.databinding.ActivityMapBinding
import com.gumibom.travelmaker.model.MarkerPosition
import com.gumibom.travelmaker.ui.main.MainViewModel
import com.gumibom.travelmaker.ui.main.findmate.meeting_post.MeetingPostActivity
import com.gumibom.travelmaker.ui.main.findmate.search.FindMateSearchFragment
import com.gumibom.travelmaker.util.PermissionChecker
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll
import java.util.concurrent.TimeUnit

private const val TAG = "FindMateActivity_싸피"
@AndroidEntryPoint
class FindMateActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding : ActivityMapBinding

    private lateinit var mMap : GoogleMap // 구글 맵
    private lateinit var fusedLocationClient: FusedLocationProviderClient // 효율적으로 위치정보를 제공
    private lateinit var locationCallback: LocationCallback
    private lateinit var permissionChecker: PermissionChecker

    private lateinit var findMateSearchFragment : FindMateSearchFragment
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionChecker = PermissionChecker(this) // 퍼미션 체커 객체 생성
        findMateSearchFragment = FindMateSearchFragment(mainViewModel)

        googleMapInit()
        getLatLng()
        requestLocationUpdates()
        observeLivaData()

        selectPlace()
        selectCategory()

        moveMeetingPost()
    }

    /**
     * 모임 생성 화면으로 넘어가기
     */
    private fun moveMeetingPost() {
        binding.fabMapMeetingAdd.setOnClickListener {
            val intent = Intent(this, MeetingPostActivity::class.java)
            startActivity(intent)
        }
    }


    // 구글 맵 초기화
    private fun googleMapInit() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // fusedLocation 초기화
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val uiSettings = mMap.uiSettings
        // 나의 현재 위치를 파란점으로 표시
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            // 권한이 없으면 설정창으로 이동한다.
            permissionChecker.moveToSettings()
        }
        // 줌 컨드롤러
        uiSettings.isZoomControlsEnabled = true

        // 줌 컨트롤러 위치 변경
        googleMap.setPadding(0, 0, 0, 250)

        openMeetingDialog()

    }

    /**
     * 내 현재 위치를 마커 없이 파란점만 표시하는 기능
     */
    fun setMyLocation(location : LatLng) {
        val zoomLevel = 15.0f // 줌 레벨을 조정하세요. 값이 클수록 더 가까워집니다.

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
    }

    /**
     * Google Map에 마커를 추가하고 해당 위치로 카메라 전환
     */
    private fun setMarker(location : LatLng, title : String) : Marker {
        val zoomLevel = 15.0f // 줌 레벨을 조정하세요. 값이 클수록 더 가까워집니다.

        val marker = mMap.addMarker(MarkerOptions().position(location).title(title))!!
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))

        return marker
    }

    /**
     * 현재 위치의 위도 경도를 받아오는 함수
     */
    private fun getLatLng() {
        var hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        // 위치 권한이 있다면
        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){

            createLocationCallback()
            Log.d(TAG, "getLatLng: 위치권한이 있나?")
        }
        // 위치 권한이 없다면
        else {
            // 권한이 없으면 설정창으로 이동한다.
            permissionChecker.moveToSettings()
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

    private fun requestLocationUpdates() {
        val locationRequest = LocationRequest()
        locationRequest.interval = TimeUnit.MINUTES.toMillis(1) // 30분마다
        locationRequest.fastestInterval = TimeUnit.MINUTES.toMillis(1)
        locationRequest.smallestDisplacement = 100f // 100m마다
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            return
        } else {
            // 권한이 없으면 설정창으로 이동한다.
            permissionChecker.moveToSettings()
        }
    }

    private fun updateLocation(latitude : Double, longitude : Double) {
        mainViewModel.currentLatitude = latitude
        mainViewModel.currentLongitude = longitude

        mainViewModel.initLatitude = latitude
        mainViewModel.initLongitude = longitude

        mainViewModel.getMarkers(latitude, longitude, 3.0)
//        setMyLocation(location)
    }

    private fun observeLivaData() {

        // 현재 위치의 변화가 있으면 마커 리스트를 받아서 마커 표시
        mainViewModel.markerList.observe(this) { markerPosition ->
            Log.d(TAG, "observeLivaData: $markerPosition")
            // 내 위치 근방에 모임이 없다면
            if (markerPosition.isEmpty()) {
                val latitude = mainViewModel.currentLatitude
                val longitude = mainViewModel.currentLongitude

                val location = LatLng(latitude, longitude)
                setMyLocation(location)
            }  // 있다면
            else {
                for (marker in markerPosition) {
                    val location = LatLng(marker.position.latitude, marker.position.longitude)
                    val title = marker.position.name
                    val googleMarker = setMarker(location, title)

                    googleMarker.tag = marker
                }
            }
        }
        mainViewModel.selectAddress.observe(this) { address ->
            // TODO 여기서 새롭게 받은 address로 서버한테 넘겨서 위치 재갱신 하기
            Log.d(TAG, "selectAddress: $address")
            val location = LatLng(address.latitude, address.longitude)
            mainViewModel.getMarkers(address.latitude, address.longitude, 3.0)
            mainViewModel.currentLatitude = address.latitude
            mainViewModel.currentLongitude = address.longitude

            setMyLocation(location)
            binding.btnFindMatePlace.text = address.title
        }


    }

    /**
     * 위치를 선택하여 검색할 수 있는 바텀 시트 다이얼로그 show
     */
    private fun selectPlace() {
        binding.btnFindMatePlace.setOnClickListener {
            findMateSearchFragment.show(supportFragmentManager, "")
        }
    }

    /**
     * 마커를 클릭했을 때 바텀 시트 다이얼로그 동작
     */
    private fun openMeetingDialog() {
        mMap.setOnMarkerClickListener { marker ->


            //바텀시트가 열리고 데이터를 받아서 띄운다.

            val markerPosition = marker.tag as MarkerPosition
            val meetingId = markerPosition.id
            //아이디 넘겨서 데이터 받고
            mainViewModel.getPostDetail(meetingId)
            //뿌리기
            mainViewModel.postDTO.observe(this){
                //

            }
            Log.d(TAG, "openMeetingDialog: $meetingId")


            true
        }
    }

    /**
     * chip을 선택하고 필터링 버튼을 눌렀을 때 필터된 결과만 서버에서 가져옴
     */
    private fun selectCategory() {
        val taste = binding.chipMapTaste
        val healing = binding.chipMapHealing
        val culture = binding.chipMapCulture
        val active = binding.chipMapActive
        val picture = binding.chipMapPicture
        val nature = binding.chipMapNature

        val categoryList : List<Chip> = listOf(taste, healing, culture, active, picture, nature)

        binding.ivMapFiltering.setOnClickListener {
            val filterCategories = mutableListOf<String>()

            for (category in categoryList) {
                // chip이 선택되어 있으면
                if (category.isCheckable) {
                    filterCategories.add(category.text.toString())
                }
            }

            // TODO 여기서 서버 통신으로 필터링
        }
    }
    companion object {
        const val REQUEST_LOCATION_PERMISSION = 100
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}