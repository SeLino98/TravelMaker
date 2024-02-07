package com.gumibom.travelmaker.ui.main.notification

import androidx.recyclerview.widget.DiffUtil
import com.gumibom.travelmaker.model.NotifyReceiveItem

class MainNotifyDiffUtil  : DiffUtil.ItemCallback<NotifyReceiveItem>() {
    override fun areItemsTheSame(oldItem: NotifyReceiveItem, newItem: NotifyReceiveItem): Boolean {
        // 고유 ID 또는 다른 고유 식별자를 사용하여 아이템이 같은지 비교
        return oldItem.requesterId == newItem.requesterId
    }

    override fun areContentsTheSame(oldItem: NotifyReceiveItem, newItem: NotifyReceiveItem): Boolean {
        // 아이템의 내용이 같은지 비교
        return oldItem == newItem
    }
}