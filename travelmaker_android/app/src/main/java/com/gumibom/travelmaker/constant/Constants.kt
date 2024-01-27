package com.gumibom.travelmaker.constant

import com.gumibom.travelmaker.BuildConfig


// 네이버, 구글 API 키
const val NAVER_ID_KEY = BuildConfig.NAVER_ID_KEY
const val NAVER_SECRET_KEY = BuildConfig.NAVER_SECRET_KEY
const val GOOGLE_API_KEY = BuildConfig.GOOGLE_API_KEY

// 네이버 장소 parameter
const val DISPLAY = 5

// 정규식
const val KOREAN_PATTERN = "^[가-힣0-9]+$"
const val ENGLISH_PATTERN = "^[a-zA-Z0-9]+$"

// Toast 메시지
const val NO_SEARCH_LOCATION = "검색하신 장소가 없습니다."
const val WRONG_INPUT = "잘못된 입력입니다. 다시 검색해주세요."

// 기타 상수
const val KOREAN = "korean"
const val ENGLISH = "english"

// 아이디 찾기 상수
const val EMPTY_NICKNAME = 0
const val WRONG_NICKNAME = 1
const val CORRECT_NICKNAME = 2
