package com.example.starwarspeople.features.character.data.remote

import com.example.starwarspeople.features.character.domain.Character
import com.google.gson.annotations.SerializedName


data class CharacterListDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<CharacterDto>
)

data class CharacterDto(
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("homeworld")
    val homeworld: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("species")
    val species: List<String>,
    @SerializedName("starships")
    val starships: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("vehicles")
    val vehicles: List<String>
){
    fun toCharacter(): Character{
        return Character(
            birthYear = birthYear,
            created = created,
            edited = edited,
            eyeColor = eyeColor,
            films = films.map { it },
            gender = gender,
            hairColor = hairColor,
            height = height,
            homeworld = homeworld,
            mass = mass,
            name = name,
            skinColor = skinColor,
            species = species.map { it },
            starships = starships.map { it },
            url = url,
            vehicles = vehicles.map { it }
        )
    }

}