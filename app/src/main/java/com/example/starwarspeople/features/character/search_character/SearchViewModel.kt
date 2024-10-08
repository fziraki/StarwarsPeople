package com.example.starwarspeople.features.character.search_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import com.example.starwarspeople.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase,
    @Named("DebounceTime") private val debounceTime: Long // Injected debounce time
): ViewModel() {

    private val _message = MutableSharedFlow<String?>()
    val message = _message.asSharedFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _characterList = MutableStateFlow<List<Character>>(emptyList())

    @OptIn(FlowPreview::class)
    val characterList = searchText
        .debounce(debounceTime)
        .combine(_characterList) { text, characters ->
            if(text.isBlank()) {
                characters
            } else {
                searchCharacter(text)
                characters
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _characterList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun searchCharacter(searchedText: String) {
        viewModelScope.launch {
            searchCharacterUseCase(searchedText).collect{ result->
                when(result){

                    is Resource.Loading -> {
                        _isSearching.update { true }
                    }

                    is Resource.Success -> {
                        _characterList.value = result.data?.map { it }?: emptyList()
                        _isSearching.update { false }
                    }

                    is Resource.Error -> {
                        _isSearching.update { false }
                        result.message?.let {
                            _message.emit(it)
                        }
                    }
                }
            }
        }
    }

}