package com.example.starwarspeople.features.character.domain

data class Character(
    val birthYear: String?,
    val films: List<String>?,
    val height: String?,
    val name: String?,
    val species: List<String>?,
    val url: String?
)