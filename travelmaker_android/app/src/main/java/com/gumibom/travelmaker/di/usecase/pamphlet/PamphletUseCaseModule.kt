package com.gumibom.travelmaker.di.usecase.pamphlet

import com.gumibom.travelmaker.data.repository.myPage.MyPageRepository
import com.gumibom.travelmaker.data.repository.pamphlet.PamphletRepository
import com.gumibom.travelmaker.domain.mypage.GetAllUserUseCase
import com.gumibom.travelmaker.domain.pamphlet.MakePamphletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PamphletUseCaseModule {

    @Singleton
    @Provides
    fun provideMakePamphletUseCase(pamphletRepository: PamphletRepository) : MakePamphletUseCase {
        return MakePamphletUseCase(pamphletRepository)
    }
}