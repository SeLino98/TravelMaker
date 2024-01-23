package com.gumibom.travelmaker.di.datasource

import com.gumibom.travelmaker.data.api.google.GoogleLocationSearchService
import com.gumibom.travelmaker.data.api.naver.NaverLocationSearchService
import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.google.GoogleLocationRemoteDataSourceImpl
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSource
import com.gumibom.travelmaker.data.datasource.naver.NaverLocationRemoteDataSourceImpl
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

}