package com.gumibom.travelmaker.ui.signup.location

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.data.dto.AddressDTO
import com.gumibom.travelmaker.databinding.ItemLocationListBinding
import com.gumibom.travelmaker.model.NaverAddress
import com.gumibom.travelmaker.ui.signup.SignupViewModel


private const val TAG = "SignupLocationAdapter_싸피"
class SignupLocationAdapter(private val context : Context, private val viewModel : SignupViewModel) : ListAdapter<NaverAddress, SignupLocationAdapter.SignupLocationViewHolder>(SignupLocationDiffUtil()) {

    // 클릭한 아이템의 position을 갱신하기 위한 변수
    private var selectItemPosition = -1
    private var previousItem = -1
    private var isSelected = false

    inner class SignupLocationViewHolder(private val binding : ItemLocationListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : NaverAddress, position : Int) {
            binding.tvItemLocationTitle.text = item.title
            binding.tvItemLocationAddress.text = item.address

            val notSelectColor = ContextCompat.getColor(context, R.color.white)
            val selectColor = ContextCompat.getColor(context, R.color.gray_a25)
            
            binding.itemLocationLayout.setOnClickListener{
                
                previousItem = selectItemPosition
                selectItemPosition = position

                Log.d(TAG, "bind position: $position")
                Log.d(TAG, "bind selectPosition: $selectItemPosition")

                // 변경된 아이템을 업데이트합니다.
                notifyItemChanged(previousItem)
                notifyItemChanged(position)
                Log.d(TAG, "bind: ${viewModel.address}")
            }

            // 배경색을 설정합니다. 선택된 아이템이면 다른 색으로 설정합니다.
            if (position == selectItemPosition) {
                if (!isSelected) {
                    binding.itemLocationLayout.setBackgroundColor(selectColor) // 선택하기
                    isSelected = true
                } else {
                    binding.itemLocationLayout.setBackgroundColor(notSelectColor) // 기본 색
                    isSelected = false
                }

                viewModel.address = item.address
            } else if (position != previousItem) {
                binding.itemLocationLayout.setBackgroundColor(notSelectColor) // 기본 색
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignupLocationViewHolder {
        val binding = ItemLocationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SignupLocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SignupLocationViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}