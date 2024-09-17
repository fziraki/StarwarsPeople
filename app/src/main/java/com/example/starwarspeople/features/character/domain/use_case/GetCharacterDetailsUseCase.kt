package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val starwarsPeopleRepository: StarwarsPeopleRepository
) {
    operator fun invoke(url: String): Flow<Resource<Character>> {
        return starwarsPeopleRepository.getCharacterDetails(url)
    }
}