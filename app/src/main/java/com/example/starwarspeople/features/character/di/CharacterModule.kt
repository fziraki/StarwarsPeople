package com.example.starwarspeople.features.character.di

import com.example.starwarspeople.features.character.data.remote.StarwarsPeopleApi
import com.example.starwarspeople.features.character.data.repository.StarwarsPeopleRepositoryImpl
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CharacterModule {

    @Provides
    @ViewModelScoped
    fun provideSearchCharacterUseCase(starwarsPeopleRepository: StarwarsPeopleRepository): SearchCharacterUseCase {
        return SearchCharacterUseCase(starwarsPeopleRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideStarwarsPeopleRepository(starwarsPeopleApi: StarwarsPeopleApi): StarwarsPeopleRepository{
        return StarwarsPeopleRepositoryImpl(starwarsPeopleApi)
    }

    @Provides
    @ViewModelScoped
    fun provideStarwarsPeopleApi(retrofit: Retrofit): StarwarsPeopleApi{
        return retrofit.create(StarwarsPeopleApi::class.java)
    }
}