package julian.scholler.starwars.navigation

sealed class Screens(val route: String) {
    object Start: Screens("start")
    object Characters: Screens("characters")
}