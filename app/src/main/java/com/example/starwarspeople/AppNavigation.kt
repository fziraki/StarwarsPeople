package com.example.starwarspeople

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarspeople.features.character.character_details.CharacterDetailsScreen
import com.example.starwarspeople.features.character.search_character.SearchScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                onCharacterClicked = {

                    val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())

                    // Navigate to the characterDetails Screen
                    navController.navigate("characterDetailsScreen/$encodedUrl")
                }
            )
        }
        composable(
            "characterDetailsScreen/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            CharacterDetailsScreen()
        }
    }
}