package com.gumibom.travelmaker.ui.main.lookpamphlets

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ItemPamphletBinding
import com.gumibom.travelmaker.model.pamphlet.PamphletItem

class LookPamphletAdapter(private val context : Context) : ListAdapter<PamphletItem, LookPamphletAdapter.LookPamphletViewHolder>(LookPamphletDiffUtil()) {

    inner class LookPamphletViewHolder(private val binding : ItemPamphletBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : PamphletItem) {
            setItem(item)
            setCategoryAdapter(item)
        }

        private fun setItem(item : PamphletItem){
            binding.tvMyRecordTitle.text = item.title

            val imageUrl = item.repreImgUrl
            Glide.with(context)
                .load(imageUrl)
                .transform(CenterCrop(), RoundedCorners(30))
                .placeholder(R.drawable.background_pamphlet)
                .into(binding.ivMyRecordPamphlet)

        }

        /**
         * 카테고리 리싸이클러뷰 선언
         */
        private fun setCategoryAdapter(item : PamphletItem) {
            val adapter = CategoryAdapter()
            binding.rvCategory.adapter = adapter
            adapter.submitList(item.categories)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookPamphletViewHolder {
        val binding = ItemPamphletBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LookPamphletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LookPamphletViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}