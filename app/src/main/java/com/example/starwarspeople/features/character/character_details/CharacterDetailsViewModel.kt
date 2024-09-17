package com.example.starwarspeople.features.character.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.Film
import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.features.character.domain.use_case.GetCharacterDetailsUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetFilmUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetHomeWorldUseCase
import com.example.starwarspeople.features.character.domain.use_case.GetSpecieUseCase
import com.example.starwarspeople.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getSpecieUseCase: GetSpecieUseCase,
    private val getHomeWorldUseCase: GetHomeWorldUseCase,
    private val getFilmUseCase: GetFilmUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _message = MutableSharedFlow<String?>()
    val message = _message.asSharedFlow()

    private val _details = MutableStateFlow<Character?>(null)
    val details = _details.asStateFlow()

    private val _specie = MutableStateFlow<Specie?>(null)
    val specie = _specie.asStateFlow()

    private val _home = MutableStateFlow<HomeWorld?>(null)
    val home = _home.asStateFlow()

    private val _films = MutableStateFlow<List<Film?>>(emptyList())
    val films = _films.asStateFlow()


    init {
        savedStateHandle.get<String>("url")?.let {
            getCharacterDetails(it)
        }
    }

    private fun getCharacterDetails(url: String) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(url).collect{ result->
                when(result){
                    is Resource.Error -> {
                        result.message?.let {
                            _message.emit(it)
                        }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _details.value = it
                            if (it.species?.isNotEmpty() == true){
                                getSpecies(it.species[0])
                            }
                            if (it.films?.isNotEmpty() == true) {
                                getFilms(it.films)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getSpecies(specie: String) {
        viewModelScope.launch {
            getSpecieUseCase(specie).collect{ result->
                when(result){
                    is Resource.Error -> {
                        result.message?.let {
                            _message.emit(it)
                        }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _specie.value = it
                            if (it.homeworld?.isNotBlank() == true){
                                getHomeWorld(it.homeworld)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getHomeWorld(homeworld: String) {
        viewModelScope.launch {
            getHomeWorldUseCase(homeworld).collect{ result->
                when(result){
                    is Resource.Error -> {
                        result.message?.let {
                            _message.emit(it)
                        }
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _home.value = it
                        }
                    }
                }
            }
        }
    }

    private fun getFilms(films: List<String>) {

        viewModelScope.launch {
            films.onEach {
                getFilmUseCase(it).collect{ result->
                    when(result){
                        is Resource.Error -> {
                            result.message?.let {
                                _message.emit(it)
                            }
                        }
                        is Resource.Loading -> {
                        }
                        is Resource.Success -> {
                            result.data?.let {
                                _films.value = _films.value.plusElement(it)
                            }
                        }
                    }
                }
            }
        }

    }


}