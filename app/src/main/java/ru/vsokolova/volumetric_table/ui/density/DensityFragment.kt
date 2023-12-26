package ru.vsokolova.volumetric_table.ui.density

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentDensityBinding
import ru.vsokolova.volumetric_table.ui.volume_table.AddDataDialogViewModel

class DensityFragment : Fragment() {

    private var _binding: FragmentDensityBinding? = null

    private val binding get() = _binding!!
    private val viewModel by lazy { DensityViewModel(Dependencies.densityRepository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDensityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinnerWoodType: MaterialAutoCompleteTextView = binding.spinnerWoodType
        val spinnerHumidity: MaterialAutoCompleteTextView = binding.spinnerHumidityValue
        val textViewResult: TextView = binding.textViewResult

        val woodTypes = resources.getStringArray(R.array.woodTypes)
        val woodAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, woodTypes)
//        spinnerWoodType.setText(woodAdapter.getItem(0))
        spinnerWoodType.setAdapter(woodAdapter)

        val humidityValues = resources.getStringArray(R.array.humidityValues)
        val humidityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, humidityValues)
//        spinnerHumidity.setText(humidityAdapter.getItem(0))
        spinnerHumidity.setAdapter(humidityAdapter)

        spinnerHumidity.setOnItemClickListener { parent, view, position, id ->
//            viewModel.getDensity(position.toString(), "1")
//            viewModel.humidity.value = position.toString()
        }

        viewModel.density.observe(viewLifecycleOwner) {
            textViewResult.text = it.first?.let { it1 -> it.second?.let { it2 ->
                viewModel.getDensity(it1,
                    it2
                )
            } }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}