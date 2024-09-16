package com.example.starwarspeople.features.character.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarwarsPeopleApi {

    @GET("people")
    suspend fun searchCharacter(
        @Query("search") name: String
    ) : CharacterListDto

    @GET
    suspend fun getCharacterDetails(
        @Url url: String
    ) : CharacterDto

    @GET
    suspend fun getSpecie(
        @Url url: String
    ) : SpecieDto

    @GET
    suspend fun getHomeWorld(
        @Url url: String
    ) : HomeWorldDto

    @GET
    suspend fun getFilm(
        @Url url: String
    ) : FilmDto
}