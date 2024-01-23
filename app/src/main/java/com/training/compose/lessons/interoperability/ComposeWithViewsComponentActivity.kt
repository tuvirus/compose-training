package com.training.compose.lessons.interoperability

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

class ComposeWithViewsComponentActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: InterOpViewModel by viewModels()
        viewModel.onSliderValueChange(intent.getFloatExtra("key",0f))
        setContent {
            MaterialTheme{
                val sliderValue by viewModel.sliderValue.observeAsState(0f)

                ComposeWithViewsIntegration(sliderValue = sliderValue, onSliderValueChange = {
                    viewModel.onSliderValueChange(it)
                }, onClick = {
                    Intent(
                        this,
                        ViewsWithComposeActivity::class.java
                    ).apply {
                        putExtra("key",sliderValue)
                    }.run {
                        startActivity(this)
                    }
                })
            }
        }
    }
}