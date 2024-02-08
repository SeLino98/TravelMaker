package com.gumibom.travelmaker.ui.main.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gumibom.travelmaker.data.dto.request.FirebaseResponseRefuseAcceptDTO
import com.gumibom.travelmaker.data.dto.request.ReceivedRequest
import com.gumibom.travelmaker.databinding.ItemFcmNotifyReceivedListBinding
import com.gumibom.travelmaker.model.NotifyReceiveItem
import com.gumibom.travelmaker.ui.main.MainViewModel

class MainFcmNotifyAdapter(private val context : Context,
                            private val viewModel: MainViewModel)
    : ListAdapter<ReceivedRequest, MainFcmNotifyAdapter.RequestViewHolder>(MainNotifyDiffUtil()) {
    // ViewBinding 타입을 변경하세요.
    // 아래 예제에서는 RequestItemLayoutBinding으로 가정합니다.
    inner class RequestViewHolder(private val binding: ItemFcmNotifyReceivedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReceivedRequest) {
            with(binding) {
                Glide.with(context).load(item.requestorImg).into(imageView)
//               ivRequesterTrust.setImageResource(item.requestorBelief)
                //신뢰도에 따라서 이미지를 매칭시켜야 됨.
                //신뢰도에 따른 이미지 매칭 메소드 구현
                tvRequesterId.text = item.requestorName
                tvGroupId.text = item.meetingPostTitle
                btnNotifyYes.setOnClickListener {
//                  // 수락 버튼 클릭 처리
                    viewModel.acceptCrew(FirebaseResponseRefuseAcceptDTO(item.meetingPostId,item.requestId,item.requestorId))
                }
                btnNotifyNo.setOnClickListener {
                    // 거절 버튼 클릭 처리
                    viewModel.acceptCrew(FirebaseResponseRefuseAcceptDTO(item.meetingPostId,item.requestId,item.requestorId))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        // ViewBinding을 사용하여 레이아웃 인플레이트
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFcmNotifyReceivedListBinding.inflate(layoutInflater, parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}