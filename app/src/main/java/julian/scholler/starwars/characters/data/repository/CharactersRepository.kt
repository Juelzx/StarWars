package julian.scholler.starwars.characters.data.repository

import julian.scholler.starwars.characters.data.api.StarWarsService
import julian.scholler.starwars.characters.data.model.Character
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val service: StarWarsService) {
    suspend fun getCharacters(): List<Character> {
        return service.getCharacters()
    }
}