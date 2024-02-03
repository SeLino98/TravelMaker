package com.gumibom.travelmaker.ui.main.findmate.meeting_post

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gumibom.travelmaker.databinding.FragmentMeetingPostCategoryBinding
import com.gumibom.travelmaker.databinding.FragmentMeetingPostDateBinding

class MeetingPostCategoryFragment : Fragment() {

    private var _binding: FragmentMeetingPostCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MeetingPostActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MeetingPostActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeetingPostCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}