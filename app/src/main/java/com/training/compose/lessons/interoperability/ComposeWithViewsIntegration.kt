package com.training.compose.lessons.interoperability

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.training.compose.databinding.InteropViewsResultBinding


@Composable
fun ComposeWithViewsIntegration(sliderValue: Float, onSliderValueChange: (Float) -> Unit, onClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            // Compose
            Slider(value = sliderValue, onValueChange = onSliderValueChange,
                valueRange = 0f..3f)

            //AndroidViewBiding
            AndroidViewBinding(
                modifier = Modifier.fillMaxWidth(),
                factory = InteropViewsResultBinding::inflate,
            ){
                resultTextView.text = sliderValue.toString()
                viewActivityButton.setOnClickListener{
                    onClick()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeWithViewsIntegrationPreview() {
    ComposeWithViewsIntegration(sliderValue = 0f, onSliderValueChange = {}, onClick = {})
}