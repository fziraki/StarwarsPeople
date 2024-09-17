package com.example.starwarspeople.features.character.di

import com.example.starwarspeople.features.character.data.remote.StarwarsPeopleApi
import com.example.starwarspeople.features.character.data.repository.StarwarsPeopleRepositoryImpl
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.features.character.domain.use_case.GetCharacterDetailsUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetFilmUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetHomeWorldUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetSpecieUseCase
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object CharacterModule {

    @Named("DebounceTime")
    @Provides
    fun provideDebounceTime(): Long {
        return 1000L // Default value
    }

    @Provides
    @ViewModelScoped
    fun provideGetFilmUseCase(starwarsPeopleRepository: StarwarsPeopleRepository): GetFilmUseCase {
        return GetFilmUseCase(starwarsPeopleRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetHomeWorldUseCase(starwarsPeopleRepository: StarwarsPeopleRepository): GetHomeWorldUseCase {
        return GetHomeWorldUseCase(starwarsPeopleRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetSpeciesUseCase(starwarsPeopleRepository: StarwarsPeopleRepository): GetSpecieUseCase {
        return GetSpecieUseCase(starwarsPeopleRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharacterDetailsUseCase(starwarsPeopleRepository: StarwarsPeopleRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(starwarsPeopleRepository)
    }

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