package julian.scholler.starwars.start.view.components

import androidx.compose.animation.core.Animatable
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
import julian.scholler.starwars.start.view.StartViewModel

@Composable
fun LightSaber(
    modifier: Modifier,
    viewModel: StartViewModel = hiltViewModel()
) {
    val lightSaberVisible by viewModel.lightSaberVisibility.collectAsStateWithLifecycle()

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

            val laserSwordPath = Path().apply {
                moveTo(
                    x = width * 0.45f, y = height * 0.8f
                )
                lineTo(
                    x = width * 0.45f, y = height * 0.3f
                )
                lineTo(
                    x = width * 0.55f, y = height * 0.3f
                )
                lineTo(
                    x = width * 0.55f, y = height * 0.8f
                )
                lineTo(
                    x = width * 0.45f, y = height * 0.8f
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
    val glowAlpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        while (true) {
            glowAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            )
            glowAlpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

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
            alpha = glowAlpha.value
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightSaberPreview() {
    LightSaber(modifier = Modifier.fillMaxSize(), viewModel = StartViewModel())
}