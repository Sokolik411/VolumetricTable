package ru.vsokolova.volumetric_table.ui

import android.widget.AdapterView
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsokolova.volumetric_table.db.DensityRepository
import javax.inject.Inject

class DensityViewModel @Inject constructor(
    private val densityRepository: DensityRepository
) : ViewModel() {

    private val _wood = MutableLiveData<String>()
    private val _humidity = MutableLiveData<String>()

    val density = MediatorLiveData<String>().apply {
        fun update() {
            val woodValue = _wood.value
            val humidityValue = _humidity.value
            if (!woodValue.isNullOrEmpty() && !humidityValue.isNullOrEmpty()) {
                viewModelScope.launch {
                    value = densityRepository.getDensity(woodValue, humidityValue)
                }
            }
        }
        addSource(_wood) { update() }
        addSource(_humidity) { update() }
    }

    val onHumidityClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
        _humidity.postValue(position.toString())
    }

    val onWoodTypeClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
        _wood.postValue(position.toString())
    }
}