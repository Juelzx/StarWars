package julian.scholler.starwars.characters.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String = "",
    @SerializedName("gender") var gender: String = "",
    @SerializedName("image") var image: String? = null,
)




