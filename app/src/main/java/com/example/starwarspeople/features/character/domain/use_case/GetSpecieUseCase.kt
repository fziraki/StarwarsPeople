package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecieUseCase @Inject constructor(
    private val starwarsPeopleRepository: StarwarsPeopleRepository
) {
    operator fun invoke(url: String): Flow<Resource<Specie>> {
        return starwarsPeopleRepository.getSpecie(url)
    }
}