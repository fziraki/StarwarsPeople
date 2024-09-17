package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.HomeWorld
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

class GetHomeWorldUseCaseTest {

    private lateinit var getHomeWorldUseCase: GetHomeWorldUseCase
    private val starwarsPeopleRepository: StarwarsPeopleRepository = mockk()

    @Before
    fun setUp() {
        getHomeWorldUseCase = GetHomeWorldUseCase(starwarsPeopleRepository)
    }

    @Test
    fun `invoke returns homeworld details successfully`() = runTest {
        val homeWorldUrl = "https://swapi.dev/api/planets/1/"
        val mockHomeWorld = HomeWorld(name = "Tatooine", population = "200000")
        val expectedResource = Resource.Success(mockHomeWorld)

        coEvery { starwarsPeopleRepository.getHomeWorld(homeWorldUrl) } returns flowOf(expectedResource)

        val result = getHomeWorldUseCase(homeWorldUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        val homeWorldUrl = "https://swapi.dev/api/planets/1/"
        val expectedResource = Resource.Error<HomeWorld>("Network Error")

        coEvery { starwarsPeopleRepository.getHomeWorld(homeWorldUrl) } returns flowOf(expectedResource)

        val result = getHomeWorldUseCase(homeWorldUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }
}
