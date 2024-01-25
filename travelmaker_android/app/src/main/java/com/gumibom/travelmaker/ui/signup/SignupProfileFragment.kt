package com.gumibom.travelmaker.ui.signup
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.FragmentSignupProfileBinding
import com.gumibom.travelmaker.ui.dialog.ClickEventDialog

private const val TAG = "SignupProfileFragment_inho"
class SignupProfileFragment : Fragment() {
    private val imagePickCode = 1000
    private val permissionCode = 1001
    private val cameraRequestCode = 1002
    private var profileFlag = false;
    private var _binding :FragmentSignupProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var signupActivity: SignupActivity;
    private val signupViewModel:SignupViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectCategory()
        selectPicture()
        observeViewModel()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach:11 ")
        //Activity 연결
        signupActivity = context as SignupActivity
    }
    private fun observeViewModel() {
//        signupViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
//            Log.d(TAG, "observeViewModel: ${favoriteList.toList()}")
//        }
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
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequestCode)
        profileFlag = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            permissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choosePhotoFromGallery()
                } else {
                    // Permission denied
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                imagePickCode -> {
                    val selectedImage = data?.data
                    binding.ivProfile.setImageURI(selectedImage)
                }
                cameraRequestCode -> {
                    val thumbnail = data?.extras?.get("data") as? Bitmap
                    binding.ivProfile.setImageBitmap(thumbnail)
                }

            }
        }
    }
    /*
    private val takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap?
            binding.ivProfile.setImageBitmap(imageBitmap)
        }
        Log.d(TAG, "selectPicture: 12#!@#")
    }
    private fun dispatchTakePicture() {
//        Log.d(TAG, "dispatchTakePicture: 1")
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
//            takePicture.launch(takePictureIntent)
//            Log.d(TAG, "dispatchTakePicture: 2")
//        }
//        Log.d(TAG, "dispatchTakePicture: 3")
    }*/

    private fun selectCategory(){
        val chipGroup:ChipGroup = binding.chipGroup
        Log.d(TAG, "selectCategory:1")
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
                    Log.d(TAG, "selctedName: ${selctedName.text.toString()}, Text: ${selctedName.id.toString()}")
                }
            }
            //모든 칩들의 공통 특성 선택
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupProfileBinding.inflate(inflater,container,false)
        return binding.root
        //inflater.inflate(R.layout.fragment_profile_signup, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}