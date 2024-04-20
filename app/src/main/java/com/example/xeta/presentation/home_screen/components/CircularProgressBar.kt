package com.example.xeta.presentation.home_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBar(
    percent: Float,
    radius: Dp = 130.dp,
    color: Color = Color.Blue,
    bgcolor: Color = Color.Blue,
    strokeWidth: Dp = 28.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    startAngle:Float=-90f,
    onClick: () -> Unit
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    // Ensure that percent is within the valid range (0 to 1)
    val validPercent =
        if (percent.isNaN() || percent < 0f) 0f else if (percent > 1f) 1f else percent

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) validPercent else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .height(radius * 2f + 10.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = bgcolor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(), cap = StrokeCap.Round
                )
            )
            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = 360 * curPercent.value,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(), cap = StrokeCap.Round
                )
            )

        }
    }
}