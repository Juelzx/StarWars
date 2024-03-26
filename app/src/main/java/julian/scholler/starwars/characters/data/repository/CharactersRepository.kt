package julian.scholler.starwars.characters.data.repository

import julian.scholler.starwars.characters.data.api.CharactersService
import julian.scholler.starwars.characters.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val service: CharactersService) {
    suspend fun getCharacters(): Flow<List<Character>> {
        return flow {
            try {
                val characters = service.getCharacters()
                emit(characters)
            } catch (e: Exception) {
                emit(emptyList())
                // show user that something went wrong
            }
        }.flowOn(Dispatchers.IO)
    }
}