package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.repository.StarwarsPeopleRepository
import com.example.starwarspeople.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharacterDetailsUseCaseTest {

    private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase
    private val starwarsPeopleRepository: StarwarsPeopleRepository = mockk()

    @Before
    fun setUp() {
        // Initialize the use case with the mocked repository
        getCharacterDetailsUseCase = GetCharacterDetailsUseCase(starwarsPeopleRepository)
    }

    @Test
    fun `invoke returns character details successfully`() = runTest {
        // Given: A sample URL and a mock character resource
        val characterUrl = "https://swapi.dev/api/people/1/"
        val mockCharacter = Character(
            name = "Luke Skywalker",
            birthYear = "19BBY",
            height = "172",
            films = listOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"),
            species = emptyList(),
            url = "https://swapi.dev/api/people/1/"
        )
        val expectedResource = Resource.Success(mockCharacter)

        // Mock the repository response
        coEvery { starwarsPeopleRepository.getCharacterDetails(characterUrl) } returns flowOf(expectedResource)

        // When: Calling the use case
        val result = getCharacterDetailsUseCase(characterUrl).toList()

        // Then: Verify that the returned flow emits the expected resource
        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        // Given: A sample URL and an error resource
        val characterUrl = "https://swapi.dev/api/people/1/"
        val expectedResource = Resource.Error<Character>("Network Error")

        // Mock the repository response
        coEvery { starwarsPeopleRepository.getCharacterDetails(characterUrl) } returns flowOf(expectedResource)

        // When: Calling the use case
        val result = getCharacterDetailsUseCase(characterUrl).toList()

        // Then: Verify that the returned flow emits the error resource
        assertEquals(listOf(expectedResource), result)
    }
}
