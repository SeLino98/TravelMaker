package com.gumibom.travelmaker.di.usecase.meeting

import com.gumibom.travelmaker.data.repository.meeting.MeetingRepository
import com.gumibom.travelmaker.data.repository.naver.NaverLocationRepository
import com.gumibom.travelmaker.domain.meeting.GetMarkerPositionsUseCase
import com.gumibom.travelmaker.domain.signup.GetNaverLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MeetingUseCaseModule {

    @Singleton
    @Provides
    fun provideGetMarkerPositionsUseCase(meetingRepository: MeetingRepository) : GetMarkerPositionsUseCase {
        return GetMarkerPositionsUseCase(meetingRepository)
    }
}