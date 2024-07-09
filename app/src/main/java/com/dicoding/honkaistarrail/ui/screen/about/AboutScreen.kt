package com.dicoding.honkaistarrail.ui.screen.about

import androidx.compose.runtime.Composable
import com.dicoding.honkaistarrail.ui.components.About

@Composable
fun AboutScreen(
    navigateBack: () -> Unit,
) {
    About(onBackClick = navigateBack)
}