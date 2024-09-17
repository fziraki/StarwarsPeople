package com.example.starwarspeople.features.character.domain.repository

import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.Film
import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StarwarsPeopleRepository {
    fun searchCharacter(name: String) : Flow<Resource<List<Character>>>
    fun getCharacterDetails(url: String) : Flow<Resource<Character>>
    fun getSpecie(url: String) : Flow<Resource<Specie>>
    fun getHomeWorld(url: String) : Flow<Resource<HomeWorld>>
    fun getFilm(url: String) : Flow<Resource<Film>>

}