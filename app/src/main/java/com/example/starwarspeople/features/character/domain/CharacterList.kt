package com.example.starwarspeople.features.character.domain


data class CharacterList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Character>
)

data class Character(
    val birthYear: String,
    val created: String,
    val edited: String,
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)