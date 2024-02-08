package com.gumibom.travelmaker.di.usecase.myPage

import com.gumibom.travelmaker.data.repository.myPage.MyPageRepository
import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import com.gumibom.travelmaker.domain.mypage.GetAllUserUseCase
import com.gumibom.travelmaker.domain.signup.CheckCertificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyPageUseCase {
    @Singleton
    @Provides
    fun provideGetAllUserUseCase(myPageRepository: MyPageRepository) : GetAllUserUseCase {
        return GetAllUserUseCase(myPageRepository)
    }
}