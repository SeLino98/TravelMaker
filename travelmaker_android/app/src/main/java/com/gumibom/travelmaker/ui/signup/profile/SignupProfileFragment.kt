package com.gumibom.travelmaker.ui.signup.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupProfileBinding
import com.gumibom.travelmaker.ui.dialog.ClickEventDialog
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

private const val TAG = "SignupProfileFragment"
@AndroidEntryPoint
class SignupProfileFragment : Fragment() {
    private val imagePickCode = 1000
    private val cameraRequestCode = 1002
    private var profileFlag = false;
    private var _binding : FragmentSignupProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var signupActivity: SignupActivity
    private val signupViewModel: SignupViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach:11 ")
        //Activity 연결
        signupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupProfileBinding.inflate(inflater, container, false)
        return binding.root
        //inflater.inflate(R.layout.fragment_profile_signup, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedChipName = mutableListOf()

        selectCategoryFirst()
        selectCategorySecond()

        selectPicture()
        observeViewModel()
        backAndNextNaviBtn()
        // 회원가입 완료 버튼을 누를 때 sharedPreference에 email이 null이 아니면 저장
    }
    private fun backAndNextNaviBtn(){
        binding.tvSignupProfilePrevious.setOnClickListener {
            signupActivity.navigateToPreviousFragment()
        }

        binding.tvSignupProfileNext.setOnClickListener {//완료 버튼 눌렀을 때

        }
    }
    private fun observeViewModel() {
        signupViewModel.isSignup.observe(viewLifecycleOwner){
            if (it.isSuccess){//회원가입이 성공했다면? 화면전환
                signupActivity.navigateToNextFragment()
            }else{
                Toast.makeText(activity, "회원가입을 실패했습니다. ", Toast.LENGTH_SHORT).show()
            }
        }
//        signupViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
//            Log.d(TAG, "observeViewModel: ${favoriteList.toList()}")
//        }
        //1. viewModel에서 리스트로 받고 옵저버로 실시간 갱신
        //2. 엘범 플래그는 구지? viewModel로 안빼도 될 듯
        //3. 두 값이 체크 됐을 때 다음버튼 활성화
    }
    private fun selectPicture(){
        //+버튼 클릭 시
        binding.ivProfileAdd.setOnClickListener {
            //사진 엘범이 열려야 됨,
            val pictureDialog = ClickEventDialog(signupActivity)
            pictureDialog.setTitle("사진 선택 유형을 선택해주세요.")
            pictureDialog.setContent("")
            val pictureDialogItems = arrayOf( "엘범에서 선택","카메라 촬영","사진 삭제")
            pictureDialog.setItems(pictureDialogItems) { _, which ->
                when (which) {
                    0 -> choosePhotoFromGallery()
                    1 -> takePhotoFromCamera()
                    2 -> deletePhotoFromProfile()
                }
            }
            binding.tvSignupProfileNext.isEnabled = profileFlag

            //권한 체크
            Log.d(TAG, "selectPicture: GHDGDG")
//            dispatchTakePicture()
            pictureDialog.show()
//            pictureDialog.clickDialogShow()
            Log.d(TAG, "selectPicture: GHDGDG2222")
        }
    }


    private fun deletePhotoFromProfile() {
        profileFlag = false
        binding.ivProfile.setBackgroundResource(R.drawable.ic_empty_profile_circle)
        binding.ivProfile.setImageResource(R.drawable.ic_empty_profile_circle)
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, imagePickCode)
        profileFlag = true;
    }
    private fun takePhotoFromCamera() {
        //권한을 설정하기
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequestCode)
        profileFlag = true
    }
    private fun getAbsolute(bitmap: Bitmap){
        // bitmap을 절대 경로 파일에 저장
        val timestamp = System.currentTimeMillis()  // 중복을 피하기 위해 현재 시간을 넣어줌
        val thumbnailFileName = "thumbnail_$timestamp.jpg"

        val thumbnailFile = File(requireContext().cacheDir, thumbnailFileName) /**.절대 로하면
         절대 값 주소나오니까 그걸 저장해뒀다가 유저데이터에 담아서 올리기 .
         */
        val fos = FileOutputStream(thumbnailFile)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.close()
    }

    //빼올 떄?

    private fun getFilePathUri(uri: Uri) : String{ //URI를 String으로
        val buildName = Build.MANUFACTURER

        // 샤오미 폰은 바로 경로 반환 가능
        if (buildName.equals("Xiaomi")) {
            return uri.path.toString()
        }

        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = requireActivity().contentResolver.query(uri, proj, null, null, null)

        if (cursor!!.moveToFirst()){
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(columnIndex)
    }

    /**
     *
     *
     * */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                imagePickCode -> {

                    //비트맵을 파일 경로로 바꾸는 로직 기존 미팅 포스트?

                    data?.data?.let { uri ->
                        Glide.with(this)
                            .load(uri)
                            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
                            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
                            .into(binding.ivProfile)
                    }
                }
                cameraRequestCode -> {

                    //비트맵을 파일 경로로 바꾸는 로직
                    val thumbnail = data?.extras?.get("data") as? Bitmap
                    thumbnail?.let {
                        Glide.with(this)
                            .load(it)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
                            .into(binding.ivProfile)
                    }
                }
            }
        }else{
            Log.d(TAG, "onActivityResult: ")
        }
    }
    //칩 그룹주ㅇㄴㄻㄹㅇㄴ
    private lateinit var selectedChip:MutableList<Int>
    private lateinit var selectedChipName : MutableList<String>
    private fun selectCategoryFirst(){
        val chipGroup: ChipGroup = binding.chipGroup1

        Log.d(TAG, "selectCategory:1")
        chipGroup.setOnCheckedStateChangeListener {
                group, checkId ->
            selectedChip= checkId;
            Log.d(TAG, "selectCategory: ${selectedChip}")
            if (selectedChip != null) {
                for (token  in selectedChip){ // 다 선택 됐고 다음 버튼을 눌렀을 때 현재 담아 있떤 리스트값들을 for문을 돌면서 유저 카테고리에 저장.
                    val selectedChipId = token //
                    val selctedName = group.findViewById<Chip>(selectedChipId)
                    selectedChipName.add(selctedName.text.toString())
                    Log.d(TAG, "Selected Chip ID: $selectedChipId, Text: $selectedChip")
                    Log.d(TAG, "selctedName: ${selctedName.text.toString()}, Text: ${selctedName.id.toString()}")
                }
            }
            //모든 칩들의 공통 특성 선택
        }
    }
    private lateinit var selectedChipSecond:MutableList<Int>
    private fun selectCategorySecond(){
        val chipGroup: ChipGroup = binding.chipGroup2
        selectedChipName = mutableListOf()
        Log.d(TAG, "selectCategory:1")
        chipGroup.setOnCheckedStateChangeListener {
                group, checkId ->
            selectedChipSecond= checkId;
            Log.d(TAG, "selectCategory: ${selectedChipSecond}")
            if (selectedChipSecond != null) {
                for (token  in selectedChipSecond){ // 다 선택 됐고 다음 버튼을 눌렀을 때 현재 담아 있떤 리스트값들을 for문을 돌면서 유저 카테고리에 저장.
                    val selectedChipId = token //
                    val selctedName = group.findViewById<Chip>(selectedChipId)
                    selectedChipName.add(selctedName.text.toString())
                    Log.d(TAG, "Selected Chip ID: $selectedChipId, Text: $selectedChipSecond")
                    Log.d(TAG, "selctedName: ${selctedName.text.toString()}, Text: ${selctedName.id.toString()}")
                }
            }
            //모든 칩들의 공통 특성 선택
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}