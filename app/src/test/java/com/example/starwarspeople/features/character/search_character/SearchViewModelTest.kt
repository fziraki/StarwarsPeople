package com.example.starwarspeople.features.character.search_character

import app.cash.turbine.test
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import com.example.starwarspeople.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val searchCharacterUseCase: SearchCharacterUseCase = mockk()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)

        viewModel = SearchViewModel(searchCharacterUseCase, debounceTime = 0L)
    }

    @After
    fun tearDown() {
        kotlinx.coroutines.Dispatchers.resetMain()
    }

    @Test
    fun `onSearchTextChange updates searchText`() = runTest {
        // Act
        viewModel.onSearchTextChange("Luke")

        // Assert
        assertEquals("Luke", viewModel.searchText.value)
    }

    @Test
    fun `searchCharacter returns success and updates characterList`() = runTest {
        // Arrange: Mock successful response
        val characters = listOf(
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
        coEvery { searchCharacterUseCase("Luke") } returns flow {
            emit(Resource.Success(characters))
        }

        // Act: Update search text to trigger search
        viewModel.onSearchTextChange("Luke")

        // Assert: Test the characterList flow
        viewModel.characterList.test {
            val result = awaitItem()
            assertEquals(characters, result)
            cancelAndIgnoreRemainingEvents() // Cancel flow collection after test
        }
    }

    @Test
    fun `searchCharacter returns error and does not update characterList`() = runTest {
        coEvery { searchCharacterUseCase("Luke") } returns flow {
            emit(Resource.Error<List<Character>>("Error"))
        }

        // Act
        viewModel.onSearchTextChange("Luke")

        // Assert no update to character list
        viewModel.characterList.test {
            assertEquals(emptyList<Character>(), awaitItem())
        }
    }

}
