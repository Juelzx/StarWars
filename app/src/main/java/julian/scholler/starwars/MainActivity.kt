package julian.scholler.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import julian.scholler.starwars.characters.data.repository.CharactersRepository
import julian.scholler.starwars.characters.view.CharactersScreen
import julian.scholler.starwars.characters.view.CharactersViewModel
import julian.scholler.starwars.navigation.Screens
import julian.scholler.starwars.start.view.StartViewModel
import julian.scholler.starwars.start.view.components.LightSaber
import julian.scholler.starwars.ui.theme.StarWarsTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var charactersRepository: CharactersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screens.Start.route) {
                        composable(Screens.Start.route) {
                            LightSaber(
                                modifier = Modifier.fillMaxSize(),
                                viewModel = hiltViewModel<StartViewModel>(),
                                navController = navController
                            )
                        }
                        composable(Screens.Characters.route) {
                            CharactersScreen(
                                modifier = Modifier.fillMaxSize(),
                                viewModel = hiltViewModel<CharactersViewModel>()
                            )
                        }
                    }
                }
            }
        }
    }
}