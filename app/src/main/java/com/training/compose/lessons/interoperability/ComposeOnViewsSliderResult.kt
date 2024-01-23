package com.training.compose.lessons.interoperability

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeOnViewsSliderResult(sliderValue: Float, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .height(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = sliderValue.toString(),
                style = MaterialTheme.typography.h5.copy(color = Color.White))
        }
        Button(onClick = onClick, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "To compose screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeOnViewsSliderResultPreview(){
    ComposeOnViewsSliderResult(sliderValue = 0f, onClick = {})
}