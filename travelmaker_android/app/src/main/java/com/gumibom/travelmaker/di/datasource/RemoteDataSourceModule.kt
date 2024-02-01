package com.gumibom.travelmaker.di.datasource

import com.gumibom.travelmaker.data.api.google.GoogleLocationSearchService
import com.gumibom.travelmaker.data.api.login.LoginService
import com.gumibom.travelmaker.data.api.naver.NaverLocationSearchService

import com.gumibom.travelmaker.data.api.signup.SignupService

import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSource
import com.gumibom.travelmaker.data.datasource.login.LoginRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSourceImpl

import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSource
import com.gumibom.travelmaker.data.datasource.signup.SignupRemoteDataSourceImpl

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
    fun provideSignupRemoteDataSource(signupService : SignupService) : SignupRemoteDataSource {
        return SignupRemoteDataSourceImpl(signupService)
    }
    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(loginService: LoginService) : LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(loginService)
    }
}