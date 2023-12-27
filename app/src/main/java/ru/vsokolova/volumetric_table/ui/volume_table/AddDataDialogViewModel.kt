package ru.vsokolova.volumetric_table.ui.volume_table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsokolova.volumetric_table.db.chips_data.ChipObject
import ru.vsokolova.volumetric_table.db.volume.VolumeRepository

class AddDataDialogViewModel(private val volumeRepository: VolumeRepository) : ViewModel()  {
    private val _lengths = MutableLiveData<List<String>>()
    private val _thicks = MutableLiveData<List<String>>()
    private val _volume = MutableLiveData<String>()
    val _amount = MutableLiveData<String>()
    val lengths: LiveData<List<String>> = _lengths
    val thicks: LiveData<List<String>> = _thicks
    val volume: LiveData<String> = _volume
    val amount: LiveData<String> = _amount

    val chipsDataValue = MediatorLiveData<ChipObject>().apply {
        fun update() {
            val amountValue = _amount.value
            val volumeValue = _volume.value
            if (!amountValue.isNullOrEmpty() && !volumeValue.isNullOrEmpty()) {
                val result = Integer.parseInt(amountValue) * volumeValue.toDouble()
                value = ChipObject(amountValue, volumeValue, result.toString())
            }
        }
        addSource(_amount) { update() }
        addSource(_volume) { update() }
    }

    fun getLengths(startWith: String, isChecked: Boolean) {
        viewModelScope.launch {
            _lengths.value = volumeRepository.getAllLength(startWith, getTop(isChecked))
        }
    }

    fun getThicks(startWith: String, length: String, isChecked: Boolean) {
        viewModelScope.launch {
            _thicks.value = volumeRepository.getAllThick(startWith, length, getTop(isChecked))
        }
    }

    fun getVolume(length: String, thick: String, isChecked: Boolean) {
        viewModelScope.launch {
            _volume.value = volumeRepository.getVolume(length, thick, getTop(isChecked))
        }
    }

    private fun getTop(isChecked: Boolean): Short {
        return if (isChecked) {
            1.toShort()
        } else {
            0.toShort()
        }
    }
}