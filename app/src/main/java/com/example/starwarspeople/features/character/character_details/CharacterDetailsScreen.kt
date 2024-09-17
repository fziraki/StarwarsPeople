package com.example.starwarspeople.features.character.character_details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwarspeople.features.character.domain.Film
import com.example.starwarspeople.features.character.domain.HomeWorld
import com.example.starwarspeople.features.character.domain.Specie
import com.example.starwarspeople.utils.toFeetAndInches

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = hiltViewModel()
){
    val characterDetails by viewModel.details.collectAsStateWithLifecycle()
    val specie by viewModel.specie.collectAsStateWithLifecycle()
    val homeWorld by viewModel.home.collectAsStateWithLifecycle()
    val films by viewModel.films.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle(null)

    val heightInFeetAndInches = characterDetails?.height?.toInt()?.toFeetAndInches()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {

            val context = LocalContext.current
            message?.let {
                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
            }

            characterDetails?.let {
                Label("character:")
                CustomText("name: ${it.name}")
                CustomText("birth year: ${it.birthYear}")
                it.height?.let {
                    CustomText("height: $it cm (${heightInFeetAndInches})")
                }
            }

            specie?.let { SpecieItem(it, homeWorld) }
            if (films.isNotEmpty()){
                Label("films:")
                FlowColumn(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    films.forEach { film ->
                        film?.let { FilmItem(it) }
                    }
                }
            }
        }
    }

}

@Composable
fun Label(text: String){
    Text(
        text,
        modifier = Modifier.padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}

@Composable
fun CustomText(text: String){
    Text(text, modifier = Modifier.padding(top = 8.dp))
}

@Composable
fun SpecieItem(specie: Specie, homeWorld: HomeWorld?){

    Label("specie:")
    CustomText("name: ${specie.name}")
    CustomText("language: ${specie.language}")
    homeWorld?.let {
        CustomText("homeworld name: ${it.name}")
        CustomText("homeworld population: ${it.population}")
    }

}

@Composable
fun FilmItem(film: Film){
    CustomText("title: ${film.title}")
    CustomText("description: ${film.openingCrawl}")
}