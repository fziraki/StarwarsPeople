package com.example.starwarspeople.features.character.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarsPeopleApi {

    @GET("people")
    suspend fun searchCharacter(
        @Query("search") name: String
    ) : CharacterListDto
}