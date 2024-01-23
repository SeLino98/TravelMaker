package com.gumibom.travelmaker.di.repository

import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSource
import com.gumibom.travelmaker.data.repository.google.GoogleLocationRepository
import com.gumibom.travelmaker.data.repository.google.GoogleLocationRepositoryImpl
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepository
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepositoryImpl
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
}