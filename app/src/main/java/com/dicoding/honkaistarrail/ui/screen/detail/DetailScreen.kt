package com.dicoding.honkaistarrail.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.honkaistarrail.di.Injection
import com.dicoding.honkaistarrail.model.HonkaiStarRailCharacters
import com.dicoding.honkaistarrail.ui.ViewModelFactory
import com.dicoding.honkaistarrail.ui.common.UiState
import com.dicoding.honkaistarrail.ui.components.HonkaiStarRailDetail

@Composable
fun DetailScreen(
    characterId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getDetailCharacter(characterId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    character = data,
                    onBackClick = navigateBack
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    character: HonkaiStarRailCharacters,
    onBackClick: () -> Unit,
) {
    HonkaiStarRailDetail(
        name = character.honkaiStarRail.name,
        photoUrl = character.honkaiStarRail.photoUrl,
        description = character.honkaiStarRail.description,
        fraction = character.honkaiStarRail.fraction,
        story = character.honkaiStarRail.story,
        voiceActor = character.honkaiStarRail.voiceActor,
        onBackClick = onBackClick
    )
}