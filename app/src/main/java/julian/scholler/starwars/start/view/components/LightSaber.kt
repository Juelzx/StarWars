package julian.scholler.starwars.start.view.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import julian.scholler.starwars.navigation.Screens
import julian.scholler.starwars.start.view.StartViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun LightSaber(
    modifier: Modifier,
    viewModel: StartViewModel = hiltViewModel(),
    navController: NavController
) {
    val lightSaberVisible by viewModel.lightSaberVisibility.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = lightSaberVisible) {
        if (lightSaberVisible) {
            delay(2.seconds)
            navController.navigate(Screens.Characters.route)
            viewModel.updateLightSaberVisibility()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ACTIVATE THE LIGHTSABER",
            color = Color.Red,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(500.dp)
    ) {
        val colors = listOf(Color.Blue, Color.Red, Color.Green)
        Saber(saberColor = colors.random(), lightSaberVisible)
        Handle { viewModel.updateLightSaberVisibility() }

    }
}

@Composable
fun Saber(
    saberColor: Color,
    visible: Boolean = false
) {
    val lightSaberColor by remember {
        mutableStateOf(saberColor)
    }
    if (visible) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val width = size.width
            val height = size.height

            val minimumX = 0.45f
            val maximumX = 0.55f
            val minimumY = 0.3f
            val maximumY = 0.8f

            val laserSwordPath = Path().apply {
                moveTo(
                    x = width * minimumX, y = height * maximumY
                )
                lineTo(
                    x = width * minimumX, y = height * minimumY
                )
                lineTo(
                    x = width * maximumX, y = height * minimumY
                )
                lineTo(
                    x = width * maximumX, y = height * maximumY
                )
                lineTo(
                    x = width * minimumX, y = height * maximumY
                )
                close()
            }
            clipPath(laserSwordPath) {
                drawRect(color = lightSaberColor)
            }
        }
    }
}

@Composable
fun Handle(onClick: () -> Unit) {

    val infiniteGlow = rememberInfiniteTransition(label = "")
    val scale by infiniteGlow.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        val width = size.width
        val height = size.height

        val handlePath = Path().apply {
            moveTo(
                x = width * 0.45f, y = height * 0.8f
            )
            lineTo(
                x = width * 0.55f, y = height * 0.8f
            )
            lineTo(
                x = width * 0.55f, y = height * 0.9f
            )
            lineTo(
                x = width * 0.45f, y = height * 0.9f
            )
            close()
        }

        clipPath(handlePath) {
            drawRect(color = Color.Black)
        }

        val circleCenterX = width * 0.5f
        val circleCenterY = height * 0.85f
        drawCircle(
            color = Color.Red,
            center = Offset(circleCenterX, circleCenterY),
            radius = 20f * 1.5f,
            alpha = scale
        )
    }
}