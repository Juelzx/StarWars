package julian.scholler.starwars.characters.data.api

import julian.scholler.starwars.characters.data.model.Character
import retrofit2.http.GET

interface StarWarsService {

    @GET("all.json")
    suspend fun getCharacters(): List<Character>
}