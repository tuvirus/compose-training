package com.training.compose.lessons.animation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun DemoScreen(title: String, backgroundColor: Color){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor),
        contentAlignment = Alignment.Center) {
        Text(text = title,
            style = MaterialTheme.typography.displayLarge)
    }
}


//for more animations see: https://developer.android.com/jetpack/compose/animation/introduction
@Preview(showBackground = true)
@Composable
private fun AnimationCrossFadeDemo(){
    var isFirstScreen by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Switch(checked = isFirstScreen, onCheckedChange = {
            isFirstScreen = it
        },
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Crossfade(targetState = isFirstScreen, label = "",
            //animation Specs
            animationSpec = keyframes {
                durationMillis = 8000
                0f at 0
                1f at 2000
                0f at 4000
                1f at 6000
            }
        ) {
            if (it) {
                DemoScreen(
                    title = "Screen One",
                    backgroundColor = Color.Gray
                )
            } else {
                DemoScreen(title = "Screen Two", backgroundColor = Color.LightGray)
            }
        }

    }

}