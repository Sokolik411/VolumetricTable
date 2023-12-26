package ru.vsokolova.volumetric_table.ui.density

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsokolova.volumetric_table.db.density.DensityRepository

class DensityViewModel(private val densityRepository: DensityRepository) : ViewModel() {

//    private val _density = MutableLiveData<String>()
    private val _wood = MutableLiveData<String>()
    private val _humidity = MutableLiveData<String>()

//    val density: LiveData<String> = _density
    val wood: LiveData<String> = _wood
    val humidity: LiveData<String> = _humidity

    val density = MediatorLiveData<Pair<String?, String?>>().apply {
        addSource(wood) { value = it to value?.second }
        addSource(humidity) { value = value?.first to it }
    }

//    density = MediatorLiveData<Pair<String?, String?>>().apply {
//        addSource(wood) { value = it to value?.second }
//        addSource(humidity) { value = value?.first to it }
//    }.observe(this) { pair ->
//        // TODO
//    }


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text

    fun getDensity(wood: String, humidity: String):String {
        viewModelScope.launch {
            val density = densityRepository.getDensity(wood, humidity)
//            _density.value = densityRepository.getDensity(wood, humidity)
        }
        return ""
    }

//    fun getDensity() {
//        viewModelScope.launch {
//            densityRepository.getDensity(wood.value.toString(), humidity.value.toString())
//        }
//    }

    //combine latest
}