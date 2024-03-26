package julian.scholler.starwars.characters.data.api

import julian.scholler.starwars.characters.data.model.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CharactersService {
    @GET("all.json")
    suspend fun getCharacters(): List<Character>
}