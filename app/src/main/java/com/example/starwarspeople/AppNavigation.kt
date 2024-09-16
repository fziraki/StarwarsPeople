package com.example.starwarspeople

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarspeople.features.character.character_details.CharacterDetailsScreen
import com.example.starwarspeople.features.character.search_character.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                onCharacterClicked = {
                    // Navigate to the characterDetails Screen
                    navController.navigate("characterDetailsScreen")
                }
            )
        }
        composable("characterDetailsScreen") {
            CharacterDetailsScreen()
        }
    }
}