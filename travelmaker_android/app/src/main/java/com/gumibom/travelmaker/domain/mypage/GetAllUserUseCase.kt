package com.gumibom.travelmaker.domain.mypage

import com.gumibom.travelmaker.data.repository.myPage.MyPageRepository
import javax.inject.Inject

class GetAllUserUseCase @Inject constructor(
    private val myPageRepositoryImpl: MyPageRepository
) {
    // TODO 여기서 null check하고 mainViewModel로 넘기기

    suspend fun getMyUserInfo()  {
        val response = myPageRepositoryImpl.getMyUserInfo()
    }
}