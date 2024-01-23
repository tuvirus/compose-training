package com.training.compose.lessons.interoperability

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InterOpViewModel: ViewModel() {

    private val _sliderValue = MutableLiveData(0.5f)

    val sliderValue: LiveData<Float> = _sliderValue

    fun onSliderValueChange(value: Float){
        _sliderValue.value = value
    }

}