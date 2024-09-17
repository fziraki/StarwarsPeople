package com.example.starwarspeople.features.character.data.repository

import com.example.starwarspeople.features.character.data.mocked_dto.MockResponses.mockCharacterListDto
import com.example.starwarspeople.features.character.data.mocked_dto.MockResponses.mockFilmDto
import com.example.starwarspeople.features.character.data.mocked_dto.MockResponses.mockHomeWorldDto
import com.example.starwarspeople.features.character.data.mocked_dto.MockResponses.mockSpecieDto
import com.example.starwarspeople.features.character.data.remote.StarwarsPeopleApi
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.Film
import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class StarwarsPeopleRepositoryImplTest {

    private lateinit var repository: StarwarsPeopleRepositoryImpl
    private val api: StarwarsPeopleApi = mockk()

    @Before
    fun setUp() {
        repository = StarwarsPeopleRepositoryImpl(api)
    }

    // searchCharacter
    @Test
    fun `searchCharacter returns success when API call is successful`() = runTest {

        val name = "Luke"

        val expectedCharacters = listOf(
            Character(
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
        )

        val expectedResource = Resource.Success(expectedCharacters)

        coEvery { api.searchCharacter(name) } returns mockCharacterListDto

        val result = repository.searchCharacter(name).toList()[1]

        assert(result is Resource.Success)
        assertEquals(expectedResource.data, (result as Resource.Success).data)

    }

    @Test
    fun `searchCharacter returns error when API call throws HttpException`() = runTest {
        val name = "Luke"
        val exceptionMessage = "Server Error"
        val statusCode = 404
        val httpException = HttpException(mockk {
            every { message() } returns exceptionMessage
            every { code() } returns statusCode
        })

        coEvery { api.searchCharacter(name) } throws httpException

        val result = repository.searchCharacter(name).toList()[1]

        assert(result is Resource.Error)
        assertEquals("HTTP 404 Server Error", (result as Resource.Error).message)
    }

    @Test
    fun `searchCharacter returns error when API call throws IOException`() = runTest {
        val name = "Luke"

        coEvery { api.searchCharacter(name) } throws IOException()

        val result = repository.searchCharacter(name).toList()[1]

        assert(result is Resource.Error)
        assertEquals("Couldn't reach server! check your connection.", (result as Resource.Error).message)
    }

    // getCharacterDetails
    @Test
    fun `getCharacterDetails returns success when API call is successful`() = runTest {

        val url = "https://swapi.dev/api/people/1/"

        val expectedCharacter =
            Character(
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

        val expectedResource = Resource.Success(expectedCharacter)

        coEvery { api.getCharacterDetails(url) } returns mockCharacterListDto.results.first()

        val result = repository.getCharacterDetails(url).toList()[1]

        assert(result is Resource.Success)
        assertEquals(expectedResource.data, (result as Resource.Success).data)
    }

    @Test
    fun `getCharacterDetails returns error when API call throws HttpException`() = runTest {

        val url = "https://swapi.dev/api/people/1/"
        val exceptionMessage = "Server Error"
        val statusCode = 404
        val httpException = HttpException(mockk {
            every { message() } returns exceptionMessage
            every { code() } returns statusCode
        })

        coEvery { api.getCharacterDetails(url) } throws httpException

        val result = repository.getCharacterDetails(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("HTTP 404 Server Error", (result as Resource.Error).message)
    }

    @Test
    fun `getCharacterDetails returns error when API call throws IOException`() = runTest {

        val url = "https://swapi.dev/api/people/1/"

        coEvery { api.getCharacterDetails(url) } throws IOException()

        val result = repository.getCharacterDetails(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("Couldn't reach server! check your connection.", (result as Resource.Error).message)
    }

    // getSpecie
    @Test
    fun `getSpecie returns success when API call is successful`() = runTest {
        val url = "https://swapi.dev/api/species/1/"

        val expectedSpecie = Specie(name = "Human", language = "Galactic Basic", homeworld = "https://swapi.dev/api/planets/9/")

        val expectedResource = Resource.Success(expectedSpecie)

        coEvery { api.getSpecie(url) } returns mockSpecieDto

        val result = repository.getSpecie(url).toList()[1]

        assert(result is Resource.Success)
        assertEquals(expectedResource.data, (result as Resource.Success).data)
    }

    @Test
    fun `getSpecie returns error when API call throws HttpException`() = runTest {

        val url = "https://swapi.dev/api/species/1/"
        val exceptionMessage = "Server Error"
        val statusCode = 404
        val httpException = HttpException(mockk {
            every { message() } returns exceptionMessage
            every { code() } returns statusCode
        })

        coEvery { api.getSpecie(url) } throws httpException

        val result = repository.getSpecie(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("HTTP 404 Server Error", (result as Resource.Error).message)
    }

    @Test
    fun `getSpecie returns error when API call throws IOException`() = runTest {
        val url = "https://swapi.dev/api/species/1/"

        coEvery { api.getSpecie(url) } throws IOException()

        val result = repository.getSpecie(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("Couldn't reach server! check your connection.", (result as Resource.Error).message)
    }

    // getHomeWorld
    @Test
    fun `getHomeWorld returns success when API call is successful`() = runTest {
        val url = "https://swapi.dev/api/planets/1/"

        val expectedHomeWorld = HomeWorld(name ="Tatooine", population = "200000")

        val expectedResource = Resource.Success(expectedHomeWorld)

        coEvery { api.getHomeWorld(url) } returns mockHomeWorldDto

        val result = repository.getHomeWorld(url).toList()[1]

        assert(result is Resource.Success)
        assertEquals(expectedResource.data, (result as Resource.Success).data)
    }

    @Test
    fun `getHomeWorld returns error when API call throws HttpException`() = runTest {
        val url = "https://swapi.dev/api/planets/1/"
        val exceptionMessage = "Server Error"
        val statusCode = 404
        val httpException = HttpException(mockk {
            every { message() } returns exceptionMessage
            every { code() } returns statusCode
        })

        coEvery { api.getHomeWorld(url) } throws httpException

        val result = repository.getHomeWorld(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("HTTP 404 Server Error", (result as Resource.Error).message)
    }

    @Test
    fun `getHomeWorld returns error when API call throws IOException`() = runTest {
        val url = "https://swapi.dev/api/planets/1/"

        coEvery { api.getHomeWorld(url) } throws IOException()

        val result = repository.getHomeWorld(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("Couldn't reach server! check your connection.", (result as Resource.Error).message)
    }

    // getFilm
    @Test
    fun `getFilm returns success when API call is successful`() = runTest {
        val url = "https://swapi.dev/api/films/1/"

        val expectedFilm = Film(
            title = "A New Hope",
            openingCrawl ="It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
            )

        val expectedResource = Resource.Success(expectedFilm)

        coEvery { api.getFilm(url) } returns mockFilmDto

        val result = repository.getFilm(url).toList()[1]

        assert(result is Resource.Success)
        assertEquals(expectedResource.data, (result as Resource.Success).data)
    }

    @Test
    fun `getFilm returns error when API call throws HttpException`() = runTest {

        val url = "https://swapi.dev/api/films/1/"
        val exceptionMessage = "Server Error"
        val statusCode = 404
        val httpException = HttpException(mockk {
            every { message() } returns exceptionMessage
            every { code() } returns statusCode
        })

        coEvery { api.getFilm(url) } throws httpException

        val result = repository.getFilm(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("HTTP 404 Server Error", (result as Resource.Error).message)

    }

    @Test
    fun `getFilm returns error when API call throws IOException`() = runTest {

        val url = "https://swapi.dev/api/films/1/"

        coEvery { api.getFilm(url) } throws IOException()

        val result = repository.getFilm(url).toList()[1]

        assert(result is Resource.Error)
        assertEquals("Couldn't reach server! check your connection.", (result as Resource.Error).message)

    }

}
