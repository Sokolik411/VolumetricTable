package ru.vsokolova.volumetric_table.ui.density

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentDensityBinding
import ru.vsokolova.volumetric_table.di.density.DensityFragmentComponent
import ru.vsokolova.volumetric_table.ui.MainActivity
import javax.inject.Inject

class DensityFragment : Fragment() {

    private var _binding: FragmentDensityBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: DensityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityFragmentComponent
            .create((requireActivity() as MainActivity).mainActivityComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDensityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            textViewResult.text = resources.getString(R.string.density_result_template, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}