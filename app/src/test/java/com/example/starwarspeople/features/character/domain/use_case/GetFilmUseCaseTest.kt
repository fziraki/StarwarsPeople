package com.example.starwarspeople.features.character.domain.use_case

import com.example.starwarspeople.features.character.domain.Film
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

class GetFilmUseCaseTest {

    private lateinit var getFilmUseCase: GetFilmUseCase
    private val starwarsPeopleRepository: StarwarsPeopleRepository = mockk()

    @Before
    fun setUp() {
        getFilmUseCase = GetFilmUseCase(starwarsPeopleRepository)
    }

    @Test
    fun `invoke returns film details successfully`() = runTest {
        val filmUrl = "https://swapi.dev/api/films/1/"
        val mockFilm = Film(
            title = "A New Hope",
            openingCrawl = "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....")
        val expectedResource = Resource.Success(mockFilm)

        coEvery { starwarsPeopleRepository.getFilm(filmUrl) } returns flowOf(expectedResource)

        val result = getFilmUseCase(filmUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        val filmUrl = "https://swapi.dev/api/films/1/"
        val expectedResource = Resource.Error<Film>("Network Error")

        coEvery { starwarsPeopleRepository.getFilm(filmUrl) } returns flowOf(expectedResource)

        val result = getFilmUseCase(filmUrl).toList()

        assertEquals(listOf(expectedResource), result)
    }
}
