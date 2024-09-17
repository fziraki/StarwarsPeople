package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchCharacterUseCaseTest {

    private lateinit var searchCharacterUseCase: SearchCharacterUseCase
    private val starwarsPeopleRepository: StarwarsPeopleRepository = mockk()

    @Before
    fun setUp() {
        searchCharacterUseCase = SearchCharacterUseCase(starwarsPeopleRepository)
    }

    @Test
    fun `invoke returns character list successfully`() = runTest {
        val characterName = "Luke"
        val mockCharacters = listOf(
            Character(
                name = "Luke Skywalker",
                birthYear = "19BBY",
                height = "172",
                films = listOf(
                    "https://swapi.dev/api/films/1/",
                    "https://swapi.dev/api/films/2/",
                    "https://swapi.dev/api/films/3/",
                    "https://swapi.dev/api/films/6/"
                ),
                species = emptyList(),
                url = "https://swapi.dev/api/people/1/"
            )
        )
        val expectedResource = Resource.Success(mockCharacters)

        coEvery { starwarsPeopleRepository.searchCharacter(characterName) } returns flowOf(expectedResource)

        val result = searchCharacterUseCase(characterName).toList()

        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns empty list when no character found`() = runTest {
        val characterName = "Unknown"
        val expectedResource = Resource.Success(emptyList<Character>())

        coEvery { starwarsPeopleRepository.searchCharacter(characterName) } returns flowOf(expectedResource)

        val result = searchCharacterUseCase(characterName).toList()

        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        val characterName = "Luke"
        val expectedResource = Resource.Error<List<Character>>("Network Error")

        coEvery { starwarsPeopleRepository.searchCharacter(characterName) } returns flowOf(expectedResource)

        val result = searchCharacterUseCase(characterName).toList()

        assertEquals(listOf(expectedResource), result)
    }
}
