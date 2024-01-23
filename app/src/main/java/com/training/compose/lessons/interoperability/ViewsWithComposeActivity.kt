package com.training.compose.lessons.interoperability

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.training.compose.databinding.InteropViewsActivityBinding

class ViewsWithComposeActivity: AppCompatActivity(){
    
    private val binding by lazy {
        InteropViewsActivityBinding.inflate(layoutInflater)
    }
    private val viewModel: InterOpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val floatFromExtras = intent.getFloatExtra("key",0f)
        viewModel.onSliderValueChange(floatFromExtras)
        binding.viewsSlider.value = floatFromExtras


        binding.viewsSlider.addOnChangeListener{ _, value, _ ->
            viewModel.onSliderValueChange(value)
        }

        binding.composeView.run {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                MaterialTheme {
                    val sliderValue by viewModel.sliderValue.observeAsState(initial = 0f)

                    ComposeOnViewsSliderResult(sliderValue = sliderValue, onClick = {
                        Intent(
                            context,
                            ComposeWithViewsComponentActivity::class.java
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

}

