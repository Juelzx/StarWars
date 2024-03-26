package julian.scholler.starwars.characters.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import julian.scholler.starwars.characters.view.components.CharacterItem

@Composable
fun CharactersScreen(
    modifier: Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val characters by viewModel.characters.collectAsState()

    LazyColumn(Modifier.fillMaxWidth()) {
        items(characters) { character ->
            CharacterItem(character = character)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
