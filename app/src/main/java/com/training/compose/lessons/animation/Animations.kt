package com.training.compose.lessons.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, group = "basic")
@Composable
private fun StateChangeSingleValue() {
    var toggled by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (toggled) Color.Green else Color.Red,
        label = "",
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            toggled = !toggled
        }) {
            Text(text = "Change color")
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .background(color = color)
                .size(100.dp)
        )
    }
}

@Preview(showBackground = true, group = "basic")
@Composable
private fun StateChangeMultipleValue() {
    var toggled by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = toggled, label = "")

    val borderWidth by transition.animateDp(label = "") {
        if (it) 10.dp else 1.dp
    }

    val degrees by transition.animateFloat(label = "") {
        if (it) -90f else 0f
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            toggled = !toggled
        }) {
            Text(text = "Change state")
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .border(
                    width = borderWidth,
                    color = Color.Black
                )
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello!",
                modifier = Modifier.rotate(
                    degrees = degrees
                )
            )
        }
    }
}

@Preview(showBackground = true, group = "visibility")
@Composable
private fun VisibilityAnimationDemo() {
    var toggled by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            toggled = !toggled
        }) {
            Text(text = "Change state")
        }

        AnimatedVisibility(
            visible = toggled,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(color = Color.Red)
                    .size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true, group = "size")
@Composable
private fun SizeAnimationDemo() {
    var size by remember { mutableFloatStateOf(1F) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Slider(
            value = size, onValueChange = {
                size = it
            },
            steps = 3,
            valueRange = 1F..3F
        )

        Text(text = "#1\n#2\n#3",
            modifier =  Modifier.fillMaxWidth()
                .padding(top = 16.dp)
                .background(color = Color.LightGray)
                .animateContentSize(),
            maxLines = size.toInt(),
            color = Color.Blue
        )

    }
}

