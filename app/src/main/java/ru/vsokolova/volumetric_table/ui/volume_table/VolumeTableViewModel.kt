package ru.vsokolova.volumetric_table.ui.volume_table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VolumeTableViewModel : ViewModel() {
    private val _volumeResult = MutableLiveData(0.0)
    val volumeResult: LiveData<Double> = _volumeResult

    fun changeVolumeResult(newResult: Double) {
        viewModelScope.launch {
            _volumeResult.value = _volumeResult.value?.plus(newResult)
        }
    }
}