package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeWorldUseCase @Inject constructor(
    private val starwarsPeopleRepository: StarwarsPeopleRepository
) {
    operator fun invoke(url: String): Flow<Resource<HomeWorld>> {
        return starwarsPeopleRepository.getHomeWorld(url)
    }
}