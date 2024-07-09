package com.dicoding.honkaistarrail.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.honkaistarrail.di.Injection
import com.dicoding.honkaistarrail.model.HonkaiStarRailCharacters
import com.dicoding.honkaistarrail.ui.ViewModelFactory
import com.dicoding.honkaistarrail.ui.common.UiState
import com.dicoding.honkaistarrail.ui.components.HonkaiStarRailList
import com.dicoding.honkaistarrail.ui.components.Search

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllCharacters()
            }

            is UiState.Success -> {
                HomeContent(
                    characters = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    onQueryChange = {
                        viewModel.searchCharacters(it)
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    characters: List<HonkaiStarRailCharacters>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    onQueryChange: (String) -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    Box(modifier = modifier) {
        LazyColumn {
            item {
                Search(
                    query = query,
                    onQueryChange = {
                        query = it
                        onQueryChange(it)
                    }
                )
            }
            items(characters, key = { it.honkaiStarRail.id }) { data ->
                HonkaiStarRailList(
                    name = data.honkaiStarRail.name,
                    avatarUrl = data.honkaiStarRail.avatarUrl,
                    description = data.honkaiStarRail.description,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.honkaiStarRail.id)
                    },
                )
            }
        }
    }
}