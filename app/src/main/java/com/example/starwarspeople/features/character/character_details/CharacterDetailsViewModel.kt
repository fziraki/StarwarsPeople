package com.example.starwarspeople.features.character.character_details

import androidx.lifecycle.ViewModel
import com.example.starwarspeople.features.character.domain.use_case.SearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase
): ViewModel() {


}