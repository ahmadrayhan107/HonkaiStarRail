package com.dicoding.honkaistarrail.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.honkaistarrail.data.HonkaiStarRailRepository
import com.dicoding.honkaistarrail.model.HonkaiStarRailCharacters
import com.dicoding.honkaistarrail.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: HonkaiStarRailRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<HonkaiStarRailCharacters>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<HonkaiStarRailCharacters>>
        get() = _uiState

    fun getDetailCharacter(characterId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDetailCharacter(characterId))
        }
    }
}