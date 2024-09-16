package com.example.starwarspeople.features.character.data.repository

import com.example.starwarspeople.features.character.data.remote.StarwarsPeopleApi
import com.example.starwarspeople.features.character.domain.Character
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

}