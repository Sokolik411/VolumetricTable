package ru.vsokolova.volumetric_table.ui.density

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentDensityBinding

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
        spinnerWoodType.onItemClickListener = viewModel.onWoodTypeClickListener

        val humidityValues = resources.getStringArray(R.array.humidityValues)
        val humidityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, humidityValues)
//        spinnerHumidity.setText(humidityAdapter.getItem(0))
        spinnerHumidity.setAdapter(humidityAdapter)

        spinnerHumidity.onItemClickListener = viewModel.onHumidityClickListener


        viewModel.density.observe(viewLifecycleOwner) {
            textViewResult.text = resources.getString(R.string.density_result, it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}