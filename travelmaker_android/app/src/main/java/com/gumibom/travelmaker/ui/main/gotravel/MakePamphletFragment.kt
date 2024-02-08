package com.gumibom.travelmaker.ui.main.gotravel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.constant.NOT_ALLOW_TITLE
import com.gumibom.travelmaker.databinding.FragmentMakePamphletBinding
import com.gumibom.travelmaker.databinding.FragmentPamphletWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MakePamphletFragment_싸피"
@AndroidEntryPoint
class MakePamphletFragment : Fragment() {

    private var _binding: FragmentMakePamphletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMakePamphletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPamphletTitle()
        moveStartPamphlet()
        uploadPamphletThumbnail()
    }

    private fun moveStartPamphlet() {
        binding.btnMakePamphletNext.setOnClickListener {
            if (binding.tiePamphletTitle.error == null && binding.tiePamphletTitle.text!!.isNotEmpty()) {
                // bundle로 제목을 다음 프래그먼트로 넘겨준다.
                val bundle = bundleOf("pamphlet_title" to binding.tiePamphletTitle.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_makePamphletFragment_to_startPamphletFragment, bundle)

            } else {
                Toast.makeText(requireContext(), NOT_ALLOW_TITLE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 팜플렛 제목의 유효성 검사
     */
    private fun checkPamphletTitle() {
        binding.tiePamphletTitle.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = binding.tiePamphletTitle.text.toString()

                if (title.length > 10) {
                    binding.tiePamphletTitle.error = "최대 10글자만 입력해주세요."
                } else {
                    binding.tiePamphletTitle.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    /**
     * 팜플렛 썸네일을 등록하는 함수
     */
    private fun uploadPamphletThumbnail() {
        binding.layoutThumbnailAdd.setOnClickListener {
            Log.d(TAG, "test: ")
        }
    }
}