package com.example.starwarspeople.features.character.search_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarspeople.features.character.domain.Character
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import com.example.starwarspeople.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _characterList = MutableStateFlow<List<Character>>(emptyList())


    @OptIn(FlowPreview::class)
    val characterList = searchText
        .debounce(1000L)
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
        _isSearching.update { true }
        viewModelScope.launch {
            searchCharacterUseCase(searchedText).collect{ result->
                when(result){
                    is Resource.Error -> {
                        _isSearching.update { false }
                    }
                    is Resource.Loading -> {
                        _isSearching.update { true }
                    }
                    is Resource.Success -> {
                        _characterList.value = result.data?.map { it }?: emptyList()
                        _isSearching.update { false }
                    }
                }
            }
        }
    }

}