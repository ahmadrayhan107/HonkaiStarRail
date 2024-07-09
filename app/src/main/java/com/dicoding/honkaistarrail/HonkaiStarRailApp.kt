package com.dicoding.honkaistarrail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.honkaistarrail.ui.navigation.Screen
import com.dicoding.honkaistarrail.ui.screen.about.AboutScreen
import com.dicoding.honkaistarrail.ui.screen.detail.DetailScreen
import com.dicoding.honkaistarrail.ui.screen.home.HomeScreen
import com.dicoding.honkaistarrail.ui.theme.HonkaiStarRailTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HonkaiStarRailApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.app_name))
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    navController.navigate(Screen.About.route)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = stringResource(id = R.string.about_page),
                                    tint = Color.Black,
                                    modifier = modifier.size(36.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                    )
                }
            ) { paddingValues ->
                HomeScreen(
                    navigateToDetail = { characterId ->
                        navController.navigate(Screen.Detail.createRoute(characterId))
                    },
                    modifier = modifier.padding(paddingValues)
                )
            }
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString("characterId") ?: "404"
            DetailScreen(
                characterId = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HonkaiStarRailAppPreview() {
    HonkaiStarRailTheme {
        HonkaiStarRailApp()
    }
}