package com.example.starwarspeople.features.character.search_character

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchScreen(
    onCharacterClicked: (url: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){

    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val characters by viewModel.characterList.collectAsStateWithLifecycle()
    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle(null)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val context = LocalContext.current
        message?.let {
            Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
        }

        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = Color.DarkGray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                itemsIndexed(characters, key = {index, item -> item.url?:index }){ index, item ->
                    Text(
                        text = item.name?:"",
                        modifier = Modifier
                            .clickable {
                                item.url?.let { onCharacterClicked(it) }
                            }
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = Color.Blue
                    )
                }

            }
        }
    }
}