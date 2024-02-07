package com.gumibom.travelmaker.model

import com.gumibom.travelmaker.data.dto.response.Position

data class PostDetail(
    val categories: List<String>,
    val dday: Int,
    val headId: Int,
    val mainImgUrl: String,
    val numOfNative: Int,
    val numOfTraveler: Int,
    val position: Position,
    val postContent: String,
    val postTitle: String,
    val profileImgUrl: String,
    val startDate: String,
    val subImgUrl: String,
    val thirdImgUrl: String
)
