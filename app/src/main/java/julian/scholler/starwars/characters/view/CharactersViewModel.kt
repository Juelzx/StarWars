package julian.scholler.starwars.characters.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import julian.scholler.starwars.characters.data.model.Character
import julian.scholler.starwars.characters.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.getCharacters().collect { fetchedCharacters ->
                fetchedCharacters.forEach {
                    println(it)
                }
                _characters.value = fetchedCharacters
            }
        }
    }
}
