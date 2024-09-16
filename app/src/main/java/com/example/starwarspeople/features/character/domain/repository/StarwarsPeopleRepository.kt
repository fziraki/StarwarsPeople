package com.example.starwarspeople.features.character.domain.repository

import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StarwarsPeopleRepository {
    fun searchCharacter(name: String) : Flow<Resource<List<Character>>>
}