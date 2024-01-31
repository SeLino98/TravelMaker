package com.gumibom.travelmaker.ui.signup.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupProfileBinding
import com.gumibom.travelmaker.ui.dialog.ClickEventDialog
import com.gumibom.travelmaker.ui.signup.SignupActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream


private const val TAG = "SignupProfileFragment"
@AndroidEntryPoint
class SignupProfileFragment : Fragment() {
    private val imagePickCode = 1000
    private val permissionCode = 1001
    private val cameraRequestCode = 1002
    private var profileFlag = false;
    private var _binding : FragmentSignupProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var signupActivity: SignupActivity
    private val signupViewModel: SignupViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Activity 연결
        signupActivity = context as SignupActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectCategory()
        selectPicture()
        observeViewModel()
        backAndNextNaviBtn()

        // TODO 회원가입 완료 버튼을 누를 때 sharedPreference에 email이 null이 아니면 저장
    }
    private fun backAndNextNaviBtn(){
        binding.tvSignupLocationPrevious.setOnClickListener {
            signupActivity.navigateToPreviousFragment()
        }
        binding.tvSignupLocationNext.setOnClickListener {
            signupActivity.navigateToNextFragment()
        }
    }
    private fun observeViewModel() {
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
    private fun saveImageToStorage(uri: Uri): Uri {
        val inputStream = signupActivity.contentResolver.openInputStream(uri)
        val directory = File(signupActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "TravelMaker")
        if (!directory.exists()) directory.mkdirs()
        val file = File(directory, "cropped_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream.use { input ->
            outputStream.use { output ->
                input?.copyTo(output)
            }
        }

        // Return the saved image URI
        return Uri.fromFile(file)
    }
    private fun handleCroppedImage(data: Intent?) {
        val resultUri = UCrop.getOutput(data!!)
        resultUri?.let {
            // Save the cropped image to phone storage and get URI
            val savedUri = saveImageToStorage(it)
            Log.d(TAG, "handleCroppedImage: ${savedUri.toString()}")
            // Display the cropped image in ImageView
            binding.ivProfile.setImageURI(savedUri)
            Log.d(TAG, "handleCroppedImage:END")
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult:!!!!!${requestCode} : ${resultCode} : ${data}")
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                imagePickCode -> {
                    val sourceUri = data?.data // Get the URI of selected image
                    Log.d(TAG, "onActivityResult: imagePickCodeLog")
                    val destinationUri = Uri.fromFile(File(signupActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "cropped_${System.currentTimeMillis()}.jpg"))
                    Log.d(TAG, "onActivityResult: ${destinationUri.toString()}")
                    UCrop.of(sourceUri!!, destinationUri)
                        .withAspectRatio(1f, 1f)
                        .start(signupActivity)
                }
                UCrop.REQUEST_CROP -> {
                    Log.d(TAG, "onActivityResult: REQUEST_CROP ${data.toString()}")
                    handleCroppedImage(data)
                    Log.d(TAG, "onActivityResult: REQUEST_CROPEND")
                }
            }
        }
    }
//    val cropActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
//            // Handle the cropped image result
//            val resultUri = UCrop.getOutput(result.data!!)
//            Log.d(TAG, "Cropped Image Uri: $resultUri")
//        }
//    }
    private fun selectCategory(){
        val chipGroup: ChipGroup = binding.chipGroup
        chipGroup.setOnCheckedStateChangeListener {
                group, checkId ->
            val selectedChip: List<Int> = checkId;
            Log.d(TAG, "selectCategory: ${selectedChip}")

            if (selectedChip != null) {
                for (token  in selectedChip){ // 다 선택 됐고 다음 버튼을 눌렀을 때 현재 담아 있떤 리스트값들을 for문을 돌면서 유저 카테고리에 저장.
                    val selectedChipId = token ///
                    val selctedName = group.findViewById<Chip>(selectedChipId)
//                  val selectedChipText = selectedChip.text.toString()
//                  val selectedChips = checkId.map { it }
//                    signupViewModel.updateFavoriteList(selctedName)
                    Log.d(TAG, "Selected Chip ID: $selectedChipId, Text: $selectedChip")
                    Log.d(
                        TAG,
                        "selctedName: ${selctedName.text.toString()}, Text: ${selctedName.id.toString()}"
                    )
                }
            }
            //모든 칩들의 공통 특성 선택
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupProfileBinding.inflate(inflater, container, false)
        return binding.root
        //inflater.inflate(R.layout.fragment_profile_signup, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}