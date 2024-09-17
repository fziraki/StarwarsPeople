package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.Specie
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

class GetSpecieUseCaseTest {

    private lateinit var getSpecieUseCase: GetSpecieUseCase
    private val starwarsPeopleRepository: StarwarsPeopleRepository = mockk()

    @Before
    fun setUp() {
        getSpecieUseCase = GetSpecieUseCase(starwarsPeopleRepository)
    }

    @Test
    fun `invoke returns specie details successfully`() = runTest {
        val specieUrl = "https://swapi.dev/api/species/1/"
        val mockSpecie = Specie(
            name = "Human",
            language = "Galactic Basic",
            homeworld = "https://swapi.dev/api/planets/9/"
        )
        val expectedResource = Resource.Success(mockSpecie)

        coEvery { starwarsPeopleRepository.getSpecie(specieUrl) } returns flowOf(expectedResource)

        val result = getSpecieUseCase(specieUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        val specieUrl = "https://swapi.dev/api/species/1/"
        val expectedResource = Resource.Error<Specie>("Network Error")

        coEvery { starwarsPeopleRepository.getSpecie(specieUrl) } returns flowOf(expectedResource)

        val result = getSpecieUseCase(specieUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }
}
