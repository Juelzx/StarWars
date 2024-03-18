package julian.scholler.starwars.start.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(): ViewModel() {

    private val _lightSaberVisibility = MutableStateFlow(false)
    val lightSaberVisibility = _lightSaberVisibility.asStateFlow()

    fun updateLightSaberVisibility() {
        _lightSaberVisibility.update { visible -> !visible }
    }
}