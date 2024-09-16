package com.example.starwarspeople.features.character.data.repository

import com.example.starwarspeople.features.character.data.remote.StarwarsPeopleApi
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.Film
import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StarwarsPeopleRepositoryImpl @Inject constructor(
    private val starwarsPeopleApi: StarwarsPeopleApi,
) : StarwarsPeopleRepository {


    override fun searchCharacter(name: String): Flow<Resource<List<Character>>> = flow {

        emit(Resource.Loading())

        try {
            val characters = starwarsPeopleApi.searchCharacter(name).results
            emit(Resource.Success(characters.map { it.toCharacter() }))
        }catch (e: HttpException){
            emit(Resource.Error(
                message = e.message?:"Something went wrong!"
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server! check your connection."
            ))
        }
    }

    override fun getCharacterDetails(url: String): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())

        try {
            val characterDetails = starwarsPeopleApi.getCharacterDetails(url)
            emit(Resource.Success(characterDetails.toCharacter()))
        }catch (e: HttpException){
            emit(Resource.Error(
                message = e.message?:"Something went wrong!"
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server! check your connection."
            ))
        }
    }

    override fun getSpecie(url: String): Flow<Resource<Specie>> = flow {
        emit(Resource.Loading())

        try {
            val specie = starwarsPeopleApi.getSpecie(url)
            emit(Resource.Success(specie.toSpecie()))
        }catch (e: HttpException){
            emit(Resource.Error(
                message = e.message?:"Something went wrong!"
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server! check your connection."
            ))
        }
    }

    override fun getHomeWorld(url: String): Flow<Resource<HomeWorld>> = flow {
        emit(Resource.Loading())

        try {
            val home = starwarsPeopleApi.getHomeWorld(url)
            emit(Resource.Success(home.toHomeWorld()))
        }catch (e: HttpException){
            emit(Resource.Error(
                message = e.message?:"Something went wrong!"
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server! check your connection."
            ))
        }
    }

    override fun getFilm(url: String): Flow<Resource<Film>> = flow {
        emit(Resource.Loading())

        try {
            val film = starwarsPeopleApi.getFilm(url)
            emit(Resource.Success(film.toFilm()))
        }catch (e: HttpException){
            emit(Resource.Error(
                message = e.message?:"Something went wrong!"
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server! check your connection."
            ))
        }
    }

}