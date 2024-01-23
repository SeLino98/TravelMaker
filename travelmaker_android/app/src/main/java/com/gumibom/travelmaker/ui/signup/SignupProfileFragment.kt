package com.gumibom.travelmaker.ui.signup
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.gumibom.travelmaker.databinding.FragmentSignupProfileBinding
private const val TAG = "SignupProfileFragment_inho"
class SignupProfileFragment : Fragment() {
    private var _binding :FragmentSignupProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var signupActivity: SignupActivity;
    private val signupViewModel:SignupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach:11 ")
        //Activity 연결
        signupActivity = context as SignupActivity
    }
    private fun observeViewModel(){

    }
    private fun selectPicture(){
        binding.ivProfileAdd.setOnClickListener {
            //사진 엘범이 열려야 됨,
            //권한 체크
        }
    }
    private fun selectCategory(){
        val chipGroup = binding.chipGroup;
        Log.d(TAG, "selectCategory:1")
        chipGroup.setOnCheckedStateChangeListener {
            group, checkId ->
            val selectedChip: List<Int> = checkId;
            Log.d(TAG, "selectCategory: ${selectedChip}")
            if (selectedChip != null) {
                for (token  in selectedChip){ // 다 선택 됐고 다음 버튼을 눌렀을 때 현재 담아 있떤 리스트값들을 for문을 돌면서 유저 카테고리에 저장.
                    val selectedChipId = token ///
                    val selctedName = group.findViewById<Chip>(selectedChipId)
//                val selectedChipText = selectedChip.text.toString()
                    Log.d("ChipSelection", "Selected Chip ID: $selectedChipId, Text: $selectedChip")
                    Log.d("selctedName", "selctedName: ${selctedName.text.toString()}, Text: ${selctedName.id.toString()}")

                }
            }
            //모든 칩들의 공통 특성 선택
        }
        Log.d(TAG, "selectCategory:2")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectCategory()
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