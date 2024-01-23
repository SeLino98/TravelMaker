package com.gumibom.travelmaker.ui.signup.location

import androidx.recyclerview.widget.DiffUtil
import com.gumibom.travelmaker.data.dto.AddressDTO
import com.gumibom.travelmaker.model.NaverAddress

class SignupLocationDiffUtil : DiffUtil.ItemCallback<NaverAddress>() {
    override fun areItemsTheSame(oldItem: NaverAddress, newItem: NaverAddress): Boolean {
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(oldItem: NaverAddress, newItem: NaverAddress): Boolean {
        return oldItem == newItem
    }
}