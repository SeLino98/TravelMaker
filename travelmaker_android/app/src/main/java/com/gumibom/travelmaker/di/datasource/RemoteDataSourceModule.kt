package com.gumibom.travelmaker.di.datasource

import com.gumibom.travelmaker.data.api.firebase.FirebaseTokenService
import com.gumibom.travelmaker.data.api.google.GoogleLocationSearchService
import com.gumibom.travelmaker.data.api.kakao.KakaoLocationSearchService
import com.gumibom.travelmaker.data.api.login.LoginService
import com.gumibom.travelmaker.data.api.meeting.MeetingService
import com.gumibom.travelmaker.data.api.naver.NaverLocationSearchService

import com.gumibom.travelmaker.data.api.signup.SignupService
import com.gumibom.travelmaker.data.datasource.firebase.FirebaseFcmRemoteDataSource
import com.gumibom.travelmaker.data.datasource.firebase.FirebaseFcmRemoteDataSourceImpl

import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.kakao.KakaoLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.kakao.KakaoLocationRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSource
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.meeting.MeetingRemoteDataSource
import com.gumibom.travelmaker.data.datasource.meeting.MeetingRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSourceImpl

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl
import dagger.Binds

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideFirebaseFcmRemoteDataSource(firebaseTokenService: FirebaseTokenService):FirebaseFcmRemoteDataSource{
        return FirebaseFcmRemoteDataSourceImpl(firebaseTokenService)

    }
    @Singleton
    @Provides
    fun provideNaverLocationRemoteDataSource(naverLocationSearchService: NaverLocationSearchService) : NaverLocationRemoteDataSource {
        return NaverLocationRemoteDataSourceImpl(naverLocationSearchService)
    }
    @Singleton
    @Provides
    fun provideGoogleLocationRemoteDataSource(googleLocationSearchService: GoogleLocationSearchService) : GoogleLocationRemoteDataSource {
        return GoogleLocationRemoteDataSourceImpl(googleLocationSearchService)
    }

    @Singleton
    @Provides
    fun provideKakaoLocationRemoteDataSource(kakaoLocationSearchService: KakaoLocationSearchService) : KakaoLocationRemoteDataSource {
        return KakaoLocationRemoteDataSourceImpl(kakaoLocationSearchService)
    }

    @Singleton
    @Provides
   fun provideSignupRemoteDataSource(signupService: SignupService): SignupRemoteDataSource {
       return SignupRemoteDataSourceImpl(signupService)
   }

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(loginService: LoginService) : LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(loginService)
    }

    @Singleton
    @Provides
    fun provideMeetingRemoteDataSource(meetingService: MeetingService) : MeetingRemoteDataSource {
        return MeetingRemoteDataSourceImpl(meetingService)
    }
}