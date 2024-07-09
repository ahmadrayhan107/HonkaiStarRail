package com.dicoding.honkaistarrail.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.honkaistarrail.data.HonkaiStarRailRepository
import com.dicoding.honkaistarrail.model.HonkaiStarRailCharacters
import com.dicoding.honkaistarrail.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HonkaiStarRailRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<HonkaiStarRailCharacters>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<HonkaiStarRailCharacters>>>
        get() = _uiState

    fun getAllCharacters() {
        viewModelScope.launch {
            repository.getAllCharacters()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { characters ->
                    _uiState.value = UiState.Success(characters)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchCharacters(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchCharacters(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                }
        }
    }
}