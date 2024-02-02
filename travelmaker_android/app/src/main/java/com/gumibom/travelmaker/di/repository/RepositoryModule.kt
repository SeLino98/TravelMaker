package com.gumibom.travelmaker.di.repository


import com.gumibom.travelmaker.data.api.signup.SignupService
import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.kakao.KakaoLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSource
import com.gumibom.travelmaker.data.datasource.meeting.MeetingRemoteDataSource
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl

import com.gumibom.travelmaker.data.repository.google.GoogleLocationRepository
import com.gumibom.travelmaker.data.repository.google.GoogleLocationRepositoryImpl
import com.gumibom.travelmaker.data.repository.kakao.KakaoLocationRepository
import com.gumibom.travelmaker.data.repository.kakao.KakaoLocationRepositoryImpl
import com.gumibom.travelmaker.data.repository.login.LoginRepository
import com.gumibom.travelmaker.data.repository.login.LoginRepositoryImpl
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepository
import com.gumibom.travelmaker.data.repository.meeting.MeetingRepositoryImpl
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepository
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepositoryImpl

import com.gumibom.travelmaker.data.repository.signup.SignupRepository
import com.gumibom.travelmaker.data.repository.signup.SignupRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNaverLocationRepository(naverLocationRemoteDataSource: NaverLocationRemoteDataSource) : NaverLocationRepository {
        return NaverLocationRepositoryImpl(naverLocationRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGoogleLocationRepository(googleLocationRemoteDataSource: GoogleLocationRemoteDataSource) : GoogleLocationRepository {
        return GoogleLocationRepositoryImpl(googleLocationRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideKakaoLocationRepository(kakaoLocationRemoteDataSource: KakaoLocationRemoteDataSource) : KakaoLocationRepository {
        return KakaoLocationRepositoryImpl(kakaoLocationRemoteDataSource)
    }

    // 회원가입 레퍼지토리
    @Singleton
    @Provides
    fun provideSignupRepository(signupRemoteDataSource : SignupRemoteDataSource) : SignupRepository {
        return SignupRepositoryImpl(signupRemoteDataSource)
    }

    // 로그인 레퍼지토리
    @Singleton
    @Provides
    fun provideLoginRepository(loginRemoteDataSource : LoginRemoteDataSource) : LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    // 모임 관련 레퍼지토리
    @Singleton
    @Provides
    fun provideMeetingRepository(meetingRemoteDataSource : MeetingRemoteDataSource) : MeetingRepository {
        return MeetingRepositoryImpl(meetingRemoteDataSource)
    }


}